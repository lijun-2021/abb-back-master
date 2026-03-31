package com.youlai.boot.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.system.model.bo.TokenBO;
import com.youlai.boot.system.model.entity.Token;
import com.youlai.boot.system.model.form.TokenForm;
import com.youlai.boot.system.model.vo.TokenPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * Token对象转换器
 */
@Mapper(componentModel = "spring")
public interface TokenConverter {

    TokenPageVO toPageVo(TokenBO bo);

    TokenPageVO toPageVo(Token entity);

    Page<TokenPageVO> toPageVo(Page<TokenBO> page);

    List<TokenPageVO> toPageVoList(List<Token> list);

    @Mapping(target = "id", ignore = true)
    Token toEntity(TokenForm form);

    @Mappings({
            @Mapping(target = "label", source = "tokenCode"),
            @Mapping(target = "value", source = "id")
    })
    Option<String> toOption(Token entity);

    List<Option<String>> toOptions(List<Token> list);

    // 将LocalDateTime转换为Long时间戳
    default Long map(LocalDateTime value) {
        return value != null ? value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() : null;
    }
}