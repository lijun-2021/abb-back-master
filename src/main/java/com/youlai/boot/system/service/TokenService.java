package com.youlai.boot.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.system.model.entity.Token;
import com.youlai.boot.system.model.form.TokenForm;
import com.youlai.boot.system.model.query.TokenPageQuery;
import com.youlai.boot.system.model.vo.TokenPageVO;

import java.util.List;

/**
 * Token业务接口
 */
public interface TokenService extends IService<Token> {

    /**
     * 获取Token分页列表
     *
     * @param queryParams 查询参数
     * @return {@link IPage<TokenPageVO>} Token分页列表
     */
    IPage<TokenPageVO> getTokenPage(TokenPageQuery queryParams);

    /**
     * 获取Token表单数据
     *
     * @param tokenId TokenID
     * @return {@link TokenForm} Token表单数据
     */
    TokenForm getTokenFormData(Long tokenId);

    /**
     * 保存Token信息
     *
     * @param tokenForm Token表单对象
     * @return {@link Boolean} 是否保存成功
     */
    boolean saveToken(TokenForm tokenForm);

    /**
     * 更新Token信息
     *
     * @param tokenId   TokenID
     * @param tokenForm Token表单对象
     * @return {@link Boolean} 是否更新成功
     */
    boolean updateToken(Long tokenId, TokenForm tokenForm);

    /**
     * 批量删除Token
     *
     * @param idsStr TokenID字符串，多个以英文逗号(,)分割
     * @return {@link Boolean} 是否删除成功
     */
    boolean deleteTokens(String idsStr);

    /**
     * 获取Token下拉选项列表
     *
     * @return {@link List<Option<String>>} Token下拉选项列表
     */
    List<Option<String>> listTokenOptions();
}