/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/
package net.somta.juggle.console.application.service.system.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.somta.core.base.page.PageParam;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.common.identity.IdentityContext;
import net.somta.juggle.console.application.assembler.system.ITokenAssembler;
import net.somta.juggle.console.application.service.system.ITokenService;
import net.somta.juggle.console.domain.system.token.TokenEntity;
import net.somta.juggle.console.domain.system.token.repository.ITokenRepository;
import net.somta.juggle.console.domain.system.token.vo.OpenApiTokenVO;
import net.somta.juggle.console.domain.system.token.vo.TokenVO;
import net.somta.juggle.console.interfaces.dto.TokenDTO;
import net.somta.juggle.console.interfaces.param.system.TokenUpdateParam;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

/**
 * @author husong
 * @since 1.1.0
 */
@Service
public class TokenServiceImpl implements ITokenService {
    private final ITokenRepository tokenRepository;

    public TokenServiceImpl(ITokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public String addToken(String tokenDesc) {
        TokenEntity tokenEntity = new TokenEntity();
        String tokenValue = tokenEntity.generateTokenValue();
        tokenRepository.addToken(tokenValue,tokenDesc);
        return tokenValue;
    }

    @Override
    public void deleteToken(Long tokenId) {
        tokenRepository.deleteTokenById(tokenId);
    }

    @Override
    public void updateToken(TokenUpdateParam tokenUpdateParam) {
        tokenRepository.updateToken(tokenUpdateParam.getId(),tokenUpdateParam.getTokenDesc());
    }

    @Override
    public Boolean isExistToken(String tokenValue) {
        // todo 这里要做一个有效期的本地缓存
        return true;
    }

    @Override
    public PageInfo getTokenPageList(PageParam pageParam) {
        Page<TokenDTO> page = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<TokenVO> tokenVoList = tokenRepository.queryTokenList();
        List<TokenDTO> tokenDtoList = ITokenAssembler.IMPL.voListToDtoList(tokenVoList);
        PageInfo pageInfo = new PageInfo(tokenDtoList);
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }
}
