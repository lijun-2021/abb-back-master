package com.youlai.boot.system.listener;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.event.AnalysisEventListener;
import com.youlai.boot.core.web.ExcelResult;
import com.youlai.boot.system.model.dto.ResourceStatusImportDTO;
import com.youlai.boot.system.model.form.ResourceStatusForm;
import com.youlai.boot.system.service.ResourceStatusService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 当日资源状态导入监听器
 */
@Slf4j
public class ResourceStatusImportListener extends AnalysisEventListener<ResourceStatusImportDTO> {

    /**
     * Excel 导入结果
     */
    @Getter
    private final ExcelResult excelResult;

    private final ResourceStatusService resourceStatusService;

    /**
     * 当前行
     */
    private Integer currentRow = 1;

    /**
     * 构造方法
     */
    public ResourceStatusImportListener() {
        this.resourceStatusService = SpringUtil.getBean(ResourceStatusService.class);
        this.excelResult = new ExcelResult();
    }

    /**
     * 每一条数据解析都会来调用
     *
     * @param resourceStatusImportDTO 一行数据
     */
    @Override
    public void invoke(ResourceStatusImportDTO resourceStatusImportDTO, AnalysisContext analysisContext) {
        log.info("解析到一条当日资源状态数据:{}", JSONUtil.toJsonStr(resourceStatusImportDTO));

        boolean validation = true;
        String errorMsg = "第" + currentRow + "行数据校验失败：";

        // 校验必填字段
        if (resourceStatusImportDTO.getProcessStationId() == null || resourceStatusImportDTO.getProcessStationId().trim().isEmpty()) {
            errorMsg += "加工位ID为空；";
            validation = false;
        }

        if (validation) {
            // 校验通过，持久化至数据库
            ResourceStatusForm resourceStatusForm = new ResourceStatusForm();
            resourceStatusForm.setProcessStationId(resourceStatusImportDTO.getProcessStationId());
            resourceStatusForm.setProcessWorkerId(resourceStatusImportDTO.getProcessWorkerId());
            resourceStatusForm.setProcessStationStatus(resourceStatusImportDTO.getProcessStationStatus());
            resourceStatusForm.setWarehouseId(resourceStatusImportDTO.getWarehouseId());
            resourceStatusForm.setWarehouseTotalFree(resourceStatusImportDTO.getWarehouseTotalFree());
            resourceStatusForm.setWarehouseCurrentFree(resourceStatusImportDTO.getWarehouseCurrentFree());
            resourceStatusForm.setWarehouseCurrentUsed(resourceStatusImportDTO.getWarehouseCurrentUsed());
            resourceStatusForm.setRgvFlag(resourceStatusImportDTO.getRgvFlag());
            resourceStatusForm.setAgvFlag(resourceStatusImportDTO.getAgvFlag());
            resourceStatusForm.setRobotArmFlag(resourceStatusImportDTO.getRobotArmFlag());

            boolean saveResult = resourceStatusService.saveResourceStatus(resourceStatusForm);
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
