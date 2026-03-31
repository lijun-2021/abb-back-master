package com.youlai.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.bo.TokenBO;
import com.youlai.boot.system.model.entity.Token;
import com.youlai.boot.system.model.form.TokenForm;
import com.youlai.boot.system.model.query.TokenPageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * Token持久层接口
 */
@Mapper
public interface TokenMapper extends BaseMapper<Token> {

    /**
     * 获取Token分页列表
     *
     * @param page        分页参数
     * @param queryParams 查询参数
     * @return Token分页列表
     */
    Page<TokenBO> getTokenPage(Page<TokenBO> page, TokenPageQuery queryParams);

    /**
     * 获取Token表单详情
     *
     * @param tokenId TokenID
     * @return Token表单详情
     */
    TokenForm getTokenFormData(Long tokenId);
}