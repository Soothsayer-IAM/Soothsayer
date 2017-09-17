package com.soothsayer.authn.service;

import com.soothsayer.authn.dto.user.UserInfoResource;
import com.soothsayer.authn.params.UserPwdCredential;
import com.soothsayer.authn.service.authn.Authenticator;
import com.soothsayer.authn.service.authn.impl.PwdAuthenticator;
import coms.soothsayer.core.entities.user.UserEntity;
import coms.soothsayer.core.utils.CodecUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private PwdAuthenticator pwdAuthenticator;

    public UserInfoResource loginWithPwd(UserPwdCredential credential) {
        //TODO:检查验证码是否正确 只有密码登录需要检查
        UserEntity userEntity = this.handleLogin(pwdAuthenticator, credential);
        //2、返回用户token
        UserInfoResource userInfoResource = new UserInfoResource();
        //TODO: 存token到redis，失效期
        userInfoResource.setSessionToken(CodecUtil.generateSessionToken(userEntity));
        userInfoResource.setStatus(UserInfoResource.Status.SUCCESS);

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
}
