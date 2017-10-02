package com.soothsayer.authn.service;

import com.soothsayer.authn.dto.user.UserInfoResource;
import com.soothsayer.authn.params.UserPwdCredential;
import com.soothsayer.authn.service.authn.Authenticator;
import com.soothsayer.authn.service.authn.impl.PwdAuthenticator;
import com.soothsayer.authn.service.user.UserService;
import com.soothsayer.core.config.RedisConfiguration;
import com.soothsayer.core.entities.user.UserEntity;
import com.soothsayer.core.utils.CodecUtil;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class LoginService {

    @Autowired
    private RedisConfiguration.RedisManager redisManager;

    @Autowired
    private PwdAuthenticator pwdAuthenticator;

    @Autowired
    private UserService userService;

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
        ResponseEntity<String> result = userService.checkUser(userEntity.getId());
        if (result.getStatusCode().is2xxSuccessful()) {
            return userEntity;
        } else {
            //TODO:用户状态检查
            log.error("Check User Failed");
            return null;
        }

    }

    public UserEntity exchangeUserInfo(String token) {
        UserEntity userEntity = (UserEntity) redisManager.getValue(token);
        //token一次性
        redisManager.removeValue(token);
        return userEntity;
    }
}
