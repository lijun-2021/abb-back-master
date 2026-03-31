package com.youlai.boot.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * Token实体
 */
@TableName("abb_token")
@Getter
@Setter
public class Token extends BaseEntity {

    /**
     * token名称
     */
    private String tokenName;

    /**
     * tokenID
     */
    private String tokenCode;

    /**
     * 门板预装时间_预
     */
    private Integer doorPreinstallTime;

    /**
     * 门板时间_预
     */
    private Integer doorTime;

    /**
     * 网格预装时间_预
     */
    private Integer gridPreinstallTime;

    /**
     * 网格时间_预
     */
    private Integer gridTime;

    /**
     * 顶板时间_预
     */
    private Integer topPlateTime;

    /**
     * 底板时间_预
     */
    private Integer bottomPlateTime;

    /**
     * 壳体组装_预
     */
    private Integer shellAssemblyTime;

    /**
     * 总成时间_预
     */
    private Integer totalAssemblyTime;

    /**
     * 创建人 ID
     */
    @TableField(fill = com.baomidou.mybatisplus.annotation.FieldFill.INSERT)
    private Long createBy;

    /**
     * 更新人 ID
     */
    @TableField(fill = com.baomidou.mybatisplus.annotation.FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 是否删除(0-否 1-是)
     */
    private Integer isDeleted;
}