package com.soothsayer.authn.service;

import com.soothsayer.authn.dto.user.UserInfoResource;
import com.soothsayer.authn.params.UserPwdCredential;
import com.soothsayer.authn.service.authn.Authenticator;
import com.soothsayer.authn.service.authn.impl.PwdAuthenticator;
import com.soothsayer.core.config.RedisConfiguration;
import com.soothsayer.core.entities.user.UserEntity;
import com.soothsayer.core.utils.CodecUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private RedisConfiguration.RedisManager redisManager;

    @Autowired
    private PwdAuthenticator pwdAuthenticator;

    public UserInfoResource loginWithPwd(UserPwdCredential credential) {
        //TODO:检查验证码是否正确 只有密码登录需要检查
        UserEntity userEntity = this.handleLogin(pwdAuthenticator, credential);
        //2、返回用户token
        UserInfoResource userInfoResource = new UserInfoResource();
        String sessionToken = CodecUtil.generateSessionToken(userEntity);
        redisManager.setValue(sessionToken, userEntity, 5, TimeUnit.MINUTES);
        userInfoResource.setStatus(UserInfoResource.Status.SUCCESS);
        userInfoResource.setSessionToken(sessionToken);
        userInfoResource.setExpiresAt(DateTime.now().plusMinutes(5).toString("YYYY-MM-dd'T'HH:mm:ss.SSSZ"));
        return userInfoResource;
    }

    /**
     * 认证及用户状态检查
     *
     * @param authenticator
     * @param credential
     * @param <T>
     * @return
     */
    private <T> UserEntity handleLogin(Authenticator<T> authenticator, T credential) {
        UserEntity userEntity = authenticator.authn(credential);
        //TODO:用户状态检查，远程调用服务
        return userEntity;
    }

    public UserEntity exchangeUserInfo(String token) {
        UserEntity userEntity = (UserEntity) redisManager.getValue(token);
        //token一次性
        redisManager.removeValue(token);
        return userEntity;
    }
}
