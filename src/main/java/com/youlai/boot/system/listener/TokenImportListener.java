package com.youlai.boot.system.listener;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.event.AnalysisEventListener;
import com.youlai.boot.core.web.ExcelResult;
import com.youlai.boot.system.model.dto.TokenImportDTO;
import com.youlai.boot.system.model.form.TokenForm;
import com.youlai.boot.system.service.TokenService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Token导入监听器
 */
@Slf4j
public class TokenImportListener extends AnalysisEventListener<TokenImportDTO> {

    /**
     * Excel 导入结果
     */
    @Getter
    private final ExcelResult excelResult;

    private final TokenService tokenService;

    /**
     * 当前行
     */
    private Integer currentRow = 1;

    /**
     * 构造方法
     */
    public TokenImportListener() {
        this.tokenService = SpringUtil.getBean(TokenService.class);
        this.excelResult = new ExcelResult();
    }

    /**
     * 每一条数据解析都会来调用
     *
     * @param tokenImportDTO 一行数据
     */
    @Override
    public void invoke(TokenImportDTO tokenImportDTO, AnalysisContext analysisContext) {
        log.info("解析到一条Token数据:{}", JSONUtil.toJsonStr(tokenImportDTO));

        boolean validation = true;
        String errorMsg = "第" + currentRow + "行数据校验失败：";

        // 校验必填字段
        if (tokenImportDTO.getTokenName() == null || tokenImportDTO.getTokenName().trim().isEmpty()) {
            errorMsg += "token名称为空；";
            validation = false;
        }

        if (tokenImportDTO.getTokenCode() == null || tokenImportDTO.getTokenCode().trim().isEmpty()) {
            errorMsg += "tokenID为空；";
            validation = false;
        }

        if (validation) {
            // 校验通过，持久化至数据库
            TokenForm tokenForm = new TokenForm();
            tokenForm.setTokenName(tokenImportDTO.getTokenName());
            tokenForm.setTokenCode(tokenImportDTO.getTokenCode());
            tokenForm.setDoorPreinstallTime(tokenImportDTO.getDoorPreinstallTime());
            tokenForm.setDoorTime(tokenImportDTO.getDoorTime());
            tokenForm.setGridPreinstallTime(tokenImportDTO.getGridPreinstallTime());
            tokenForm.setGridTime(tokenImportDTO.getGridTime());
            tokenForm.setTopPlateTime(tokenImportDTO.getTopPlateTime());
            tokenForm.setBottomPlateTime(tokenImportDTO.getBottomPlateTime());
            tokenForm.setShellAssemblyTime(tokenImportDTO.getShellAssemblyTime());
            tokenForm.setTotalAssemblyTime(tokenImportDTO.getTotalAssemblyTime());

            boolean saveResult = tokenService.saveToken(tokenForm);
            if (saveResult) {
                excelResult.setValidCount(excelResult.getValidCount() + 1);
            } else {
                excelResult.setInvalidCount(excelResult.getInvalidCount() + 1);
                errorMsg += "第" + currentRow + "行数据保存失败；";
                excelResult.getMessageList().add(errorMsg);
            }
        } else {
            excelResult.setInvalidCount(excelResult.getInvalidCount() + 1);
            excelResult.getMessageList().add(errorMsg);
        }
        currentRow++;
    }

    /**
     * 所有数据解析完成会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成！");
    }
}
