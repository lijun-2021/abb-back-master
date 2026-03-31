package com.youlai.boot.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.common.util.CharsetDetectorUtil;
import com.youlai.boot.system.converter.ProcessingResultConverter;
import com.youlai.boot.system.mapper.AbbProcessingResultMapper;
import com.youlai.boot.system.model.bo.ProcessingResultBO;
import com.youlai.boot.system.model.entity.AbbProcessingResult;
import com.youlai.boot.system.model.query.ProcessingResultPageQuery;
import com.youlai.boot.system.model.vo.ProcessingResultPageVO;
import com.youlai.boot.system.service.AbbProcessingResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import com.youlai.boot.system.model.vo.OutputDetailVO;

/**
 * C++算法处理结果业务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AbbProcessingResultServiceImpl extends ServiceImpl<AbbProcessingResultMapper, AbbProcessingResult> implements AbbProcessingResultService {

    @Override
    public IPage<ProcessingResultPageVO> getProcessingResultPage(ProcessingResultPageQuery queryParams) {
        // 构建分页对象
        Page<ProcessingResultBO> page = new Page<>(queryParams.getPageNum(), queryParams.getPageSize());
        
        // 调用mapper层查询
        Page<ProcessingResultBO> boPage = this.baseMapper.getProcessingResultPage(page, queryParams);
        
        // 使用Converter转换为VO对象
        return ProcessingResultConverter.INSTANCE.toPageVo(boPage);
    }

    @Override
    public boolean deleteProcessingResults(String ids) {
        try {
            // 将ids字符串分割为Long列表
            List<Long> idList = new ArrayList<>();
            StringTokenizer tokenizer = new StringTokenizer(ids, ",");
            while (tokenizer.hasMoreTokens()) {
                idList.add(Long.parseLong(tokenizer.nextToken()));
            }
            
            // 批量删除（逻辑删除）
            return this.removeByIds(idList);
        } catch (Exception e) {
            log.error("批量删除C++算法处理结果失败", e);
            return false;
        }
    }
    /**
     * 下载上传的文件目录
     *
     * @param uploadDir 上传目录的UUID
     * @return {@link java.io.File} 打包后的zip文件
     */
    @Override
    public File downloadFiles(String uploadDir) {
        try {
            // 源目录路径
            String sourceDirPath = System.getProperty("user.dir") + File.separator + "tmp" + File.separator + uploadDir;
            File sourceDir = new File(sourceDirPath);

            // 检查目录是否存在
            if (!sourceDir.exists() || !sourceDir.isDirectory()) {
                throw new RuntimeException("上传目录不存在");
            }

            // 创建zip文件
            String zipFilePath = System.getProperty("user.dir") + File.separator + "tmp" + File.separator + uploadDir + ".zip";
            File zipFile = new File(zipFilePath);

            // 使用ZipOutputStream打包文件
            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
                // 递归打包目录
                addFilesToZip(sourceDir, sourceDir.getName(), zos);
            }

            log.info("目录 {} 已打包为 zip 文件: {}", uploadDir, zipFilePath);
            return zipFile;
        } catch (IOException e) {
            log.error("打包文件失败", e);
            throw new RuntimeException("打包文件失败: " + e.getMessage());
        }
    }

    /**
     * 递归将文件添加到zip输出流
     *
     * @param file 文件或目录
     * @param basePath 基础路径（用于zip内部的文件路径）
     * @param zos Zip输出流
     * @throws IOException IO异常
     */
    private void addFilesToZip(File file, String basePath, ZipOutputStream zos) throws IOException {
        if (file.isDirectory()) {
            // 创建目录条目
            zos.putNextEntry(new ZipEntry(basePath + "/"));
            zos.closeEntry();

            // 递归处理子文件
            File[] files = file.listFiles();
            if (files != null) {
                for (File subFile : files) {
                    addFilesToZip(subFile, basePath + "/" + subFile.getName(), zos);
                }
            }
        } else {
            // 创建文件条目
            zos.putNextEntry(new ZipEntry(basePath));

            // 写入文件内容
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
            }

            zos.closeEntry();
        }
    }

    @Override
    public List<OutputDetailVO> ganttData(String uploadDir) {
        // 构建文件路径
        String sourceDirPath = System.getProperty("user.dir")
                + File.separator + "tmp"
                + File.separator + uploadDir;
        File outputDetailFile = new File(sourceDirPath, "output_detail.txt");

        if (!outputDetailFile.exists() || !outputDetailFile.isFile()) {
            throw new RuntimeException("output_detail.txt 文件不存在");
        }

        List<OutputDetailVO> resultList = new ArrayList<>();

        try {
            // 自动识别编码（UTF-8 / GBK / 其他）
            Charset charset = CharsetDetectorUtil.detect(outputDetailFile);
            log.info("output_detail.txt detected charset: {}", charset.name());

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(outputDetailFile), charset))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();

                    if (!line.contains("激发变迁:")) {
                        continue;
                    }

                    // 提取 transition
                    String transition = line.substring(
                            line.indexOf("激发变迁:") + 5
                    ).trim();

                    if (!("t0-1".equals(transition) || "td0-1".equals(transition))) {
                        continue;
                    }

                    OutputDetailVO vo = new OutputDetailVO();

                    // 当前加工时间
                    int processTimeIndex = line.indexOf("当前加工时间:");
                    if (processTimeIndex != -1) {
                        String processTimeStr = line.substring(
                                processTimeIndex + 7,
                                line.indexOf(",", processTimeIndex)
                        ).trim();
                        vo.setProcessTime(Integer.parseInt(processTimeStr));
                    }

                    // 工件ID
                    int workpieceIdIndex = line.indexOf("工件ID:");
                    if (workpieceIdIndex != -1) {
                        String workpieceIdStr = line.substring(
                                workpieceIdIndex + 5,
                                line.indexOf(",", workpieceIdIndex)
                        ).trim();
                        vo.setWorkpieceId(Integer.parseInt(workpieceIdStr));
                    }

                    vo.setTransition(transition);
                    resultList.add(vo);
                }
            }

            return resultList;

        } catch (IOException e) {
            log.error("解析 output_detail.txt 失败", e);
            throw new RuntimeException("解析文件失败: " + e.getMessage());
        } catch (NumberFormatException e) {
            log.error("数字字段解析失败", e);
            throw new RuntimeException("解析数字字段失败: " + e.getMessage());
        }
    }
}
