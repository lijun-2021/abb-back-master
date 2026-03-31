package com.youlai.boot.system.model.bo;

import lombok.Data;

/**
 * Token业务对象
 */
@Data
public class TokenBO {

    /**
     * 主键ID
     */
    private Long id;

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
}