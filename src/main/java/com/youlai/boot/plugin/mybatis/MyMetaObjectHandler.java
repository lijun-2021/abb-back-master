package com.youlai.boot.plugin.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.youlai.boot.system.model.entity.SwitchCabinet;
import com.youlai.boot.system.model.entity.EmployeeTask;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * mybatis-plus 字段自动填充
 *
 * @author haoxr
 * @since 2022/10/14
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 新增填充创建时间
     *
     * @param metaObject 元数据
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
    }

    /**
     * 更新填充更新时间
     *
     * @param metaObject 元数据
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Object originalObject = metaObject.getOriginalObject();

        // 只有 SwitchCabinet和EmployeeTask 实体强制更新 updateTime
        if (originalObject instanceof SwitchCabinet|| originalObject instanceof EmployeeTask) {
            metaObject.setValue("updateTime", LocalDateTime.now());
        } else {
            // 其他表保持原有逻辑：仅当 updateTime 为 null 时才更新
            this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        }
    }

}
