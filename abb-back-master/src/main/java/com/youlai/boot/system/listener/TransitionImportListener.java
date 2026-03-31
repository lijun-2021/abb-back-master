package com.youlai.boot.system.listener;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.event.AnalysisEventListener;
import com.youlai.boot.core.web.ExcelResult;
import com.youlai.boot.system.model.dto.TransitionImportDTO;
import com.youlai.boot.system.model.form.TransitionForm;
import com.youlai.boot.system.service.TransitionService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 变迁数据导入监听器
 */
@Slf4j
public class TransitionImportListener extends AnalysisEventListener<TransitionImportDTO> {

    /**
     * Excel 导入结果
     */
    @Getter
    private final ExcelResult excelResult;

    private final TransitionService transitionService;

    /**
     * 当前行
     */
    private Integer currentRow = 1;

    /**
     * 构造方法
     */
    public TransitionImportListener() {
        this.transitionService = SpringUtil.getBean(TransitionService.class);
        this.excelResult = new ExcelResult();
    }

    /**
     * 每一条数据解析都会来调用
     *
     * @param transitionImportDTO 一行数据
     */
    @Override
    public void invoke(TransitionImportDTO transitionImportDTO, AnalysisContext analysisContext) {
        log.info("解析到一条变迁数据:{}", JSONUtil.toJsonStr(transitionImportDTO));

        boolean validation = true;
        String errorMsg = "第" + currentRow + "行数据校验失败：";

        // 校验必填字段
        if (transitionImportDTO.getTransitionName() == null || transitionImportDTO.getTransitionName().trim().isEmpty()) {
            errorMsg += "变迁名称为空；";
            validation = false;
        }

        if (validation) {
            // 校验通过，持久化至数据库
            TransitionForm transitionForm = new TransitionForm();
            transitionForm.setTransitionName(transitionImportDTO.getTransitionName());
            transitionForm.setPrePlace(transitionImportDTO.getPrePlace());
            transitionForm.setPostPlace(transitionImportDTO.getPostPlace());
            transitionForm.setPlaceCheckFlag(transitionImportDTO.getPlaceCheckFlag());
            transitionForm.setPlaceLockFlag(transitionImportDTO.getPlaceLockFlag());

            boolean saveResult = transitionService.saveTransition(transitionForm);
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
