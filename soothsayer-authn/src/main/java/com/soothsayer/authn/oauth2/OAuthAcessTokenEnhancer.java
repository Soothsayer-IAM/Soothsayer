package com.soothsayer.authn.oauth2;

import org.springframework.security.oauth2.common.*;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Date;

public class OAuthAcessTokenEnhancer implements TokenEnhancer {

    private RandomValueStringGenerator generator = new RandomValueStringGenerator(40);

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken result = new DefaultOAuth2AccessToken(accessToken);
        result.setValue(generator.generate());
        OAuth2RefreshToken refreshToken = result.getRefreshToken();
        if (refreshToken != null) {
            DefaultOAuth2RefreshToken encodeRefreshToken = new DefaultOAuth2RefreshToken(generator.generate());
            if (refreshToken instanceof ExpiringOAuth2RefreshToken) {
                Date expiration = ((ExpiringOAuth2RefreshToken) refreshToken).getExpiration();
                encodeRefreshToken = new DefaultExpiringOAuth2RefreshToken(encodeRefreshToken.getValue(), expiration);
            }
            result.setRefreshToken(encodeRefreshToken);
        }
        return result;
    }
}
