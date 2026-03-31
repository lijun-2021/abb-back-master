package com.youlai.boot.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.security.util.SecurityUtils;
import com.youlai.boot.system.converter.TokenConverter;
import com.youlai.boot.system.mapper.TokenMapper;
import com.youlai.boot.system.model.bo.TokenBO;
import com.youlai.boot.system.model.entity.Token;
import com.youlai.boot.system.model.form.TokenForm;
import com.youlai.boot.system.model.query.TokenPageQuery;
import com.youlai.boot.system.model.vo.TokenPageVO;
import com.youlai.boot.system.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Token业务实现类
 */
@Service
@RequiredArgsConstructor
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService {

    private final TokenConverter tokenConverter;

    /**
     * 获取Token分页列表
     *
     * @param queryParams 查询参数
     * @return {@link IPage<TokenPageVO>} Token分页列表
     */
    @Override
    public IPage<TokenPageVO> getTokenPage(TokenPageQuery queryParams) {
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<TokenBO> page = new Page<>(pageNum, pageSize);

        // 查询数据
        Page<TokenBO> tokenPage = this.baseMapper.getTokenPage(page, queryParams);

        // 实体转换
        return tokenConverter.toPageVo(tokenPage);
    }

    /**
     * 获取Token表单数据
     *
     * @param tokenId TokenID
     * @return {@link TokenForm} Token表单数据
     */
    @Override
    public TokenForm getTokenFormData(Long tokenId) {
        return this.baseMapper.getTokenFormData(tokenId);
    }

    /**
     * 保存Token信息
     *
     * @param tokenForm Token表单对象
     * @return {@link Boolean} 是否保存成功
     */
    @Override
    public boolean saveToken(TokenForm tokenForm) {
        // 实体转换 form->entity
        Token entity = tokenConverter.toEntity(tokenForm);
        entity.setCreateBy(SecurityUtils.getUserId());
        entity.setUpdateBy(SecurityUtils.getUserId());

        // 保存Token
        return this.save(entity);
    }

    /**
     * 更新Token信息
     *
     * @param tokenId   TokenID
     * @param tokenForm Token表单对象
     * @return {@link Boolean} 是否更新成功
     */
    @Override
    public boolean updateToken(Long tokenId, TokenForm tokenForm) {
        // form -> entity
        Token entity = tokenConverter.toEntity(tokenForm);
        entity.setId(tokenId);
        entity.setUpdateBy(SecurityUtils.getUserId());

        // 更新Token
        return this.updateById(entity);
    }

    /**
     * 批量删除Token
     *
     * @param idsStr TokenID，多个以英文逗号(,)分割
     * @return {@link Boolean} 是否删除成功
     */
    @Override
    public boolean deleteTokens(String idsStr) {
        if (StrUtil.isBlank(idsStr)) {
            return false;
        }
        // 逻辑删除
        List<Long> ids = Arrays.stream(idsStr.split("\\,")).map(Long::parseLong).collect(Collectors.toList());
        return this.removeByIds(ids);
    }

    /**
     * 获取Token下拉列表
     *
     * @return {@link List<Option<String>>} Token选项列表
     */
    @Override
    public List<Option<String>> listTokenOptions() {
        List<Token> tokens = this.list(new LambdaQueryWrapper<Token>().orderByAsc(Token::getId));
        return tokenConverter.toOptions(tokens);
    }
}