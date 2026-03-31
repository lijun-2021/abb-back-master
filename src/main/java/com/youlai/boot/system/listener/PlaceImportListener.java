package com.youlai.boot.system.listener;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.event.AnalysisEventListener;
import com.youlai.boot.core.web.ExcelResult;
import com.youlai.boot.system.model.dto.PlaceImportDTO;
import com.youlai.boot.system.model.form.PlaceForm;
import com.youlai.boot.system.service.PlaceService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 库所导入监听器
 */
@Slf4j
public class PlaceImportListener extends AnalysisEventListener<PlaceImportDTO> {

    /**
     * Excel 导入结果
     */
    @Getter
    private final ExcelResult excelResult;

    private final PlaceService placeService;

    /**
     * 当前行
     */
    private Integer currentRow = 1;

    /**
     * 构造方法
     */
    public PlaceImportListener() {
        this.placeService = SpringUtil.getBean(PlaceService.class);
        this.excelResult = new ExcelResult();
    }

    /**
     * 每一条数据解析都会来调用
     *
     * @param placeImportDTO 一行数据
     */
    @Override
    public void invoke(PlaceImportDTO placeImportDTO, AnalysisContext analysisContext) {
        log.info("解析到一条库所数据:{}", JSONUtil.toJsonStr(placeImportDTO));

        boolean validation = true;
        String errorMsg = "第" + currentRow + "行数据校验失败：";

        // 校验必填字段
        if (placeImportDTO.getPlaceName() == null || placeImportDTO.getPlaceName().trim().isEmpty()) {
            errorMsg += "库所名称为空；";
            validation = false;
        }

        if (validation) {
            // 校验通过，持久化至数据库
            PlaceForm placeForm = new PlaceForm();
            placeForm.setPlaceName(placeImportDTO.getPlaceName());
            placeForm.setCapacity(placeImportDTO.getCapacity());
            placeForm.setPreTransition(placeImportDTO.getPreTransition());
            placeForm.setPostTransition(placeImportDTO.getPostTransition());
            placeForm.setPlaceType(placeImportDTO.getPlaceType());
            placeForm.setTimedPlaceFlag(placeImportDTO.getTimedPlaceFlag());
            placeForm.setProcessStationId(placeImportDTO.getProcessStationId());
            placeForm.setStageFlag(placeImportDTO.getStageFlag());
            placeForm.setPlaceColor(placeImportDTO.getPlaceColor());

            boolean saveResult = placeService.savePlace(placeForm);
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
