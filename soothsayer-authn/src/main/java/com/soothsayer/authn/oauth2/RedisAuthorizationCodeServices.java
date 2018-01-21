package com.soothsayer.authn.oauth2;

import com.soothsayer.core.utils.RedisManager;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;

import java.util.concurrent.TimeUnit;

public class RedisAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {

    private RandomValueStringGenerator generator = new RandomValueStringGenerator(20);

    private RedisManager redisManager;

    public RedisAuthorizationCodeServices(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

    @Override
    public String createAuthorizationCode(OAuth2Authentication authentication) {
        String code = generator.generate();
        this.store(code, authentication);
        return code;
    }

    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        redisManager.setValue(code, authentication, 2, TimeUnit.MINUTES);
    }

    @Override
    protected OAuth2Authentication remove(String code) {
        OAuth2Authentication auth2Authentication = (OAuth2Authentication) redisManager.getValue(code);
        if (auth2Authentication != null) {
            redisManager.removeValue(code);
        }
        return auth2Authentication;
    }
}
