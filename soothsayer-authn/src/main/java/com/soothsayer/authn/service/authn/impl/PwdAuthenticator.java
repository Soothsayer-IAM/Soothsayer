package com.soothsayer.authn.service.authn.impl;

import com.soothsayer.authn.dao.user.UserDAO;
import com.soothsayer.authn.params.UserPwdCredential;
import com.soothsayer.authn.service.authn.Authenticator;
import com.soothsayer.core.entities.user.UserEntity;
import com.soothsayer.core.utils.AESEncryptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PwdAuthenticator implements Authenticator<UserPwdCredential> {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserEntity authn(UserPwdCredential credential) {
        UserEntity userEntity = userDAO.findByUserName(credential.getUserName());
        if (userEntity == null) {
            log.info("{}用户名或密码错误", credential.getUserName());
            //TODO: 记入审计日志
        }
        String password = AESEncryptionUtil.encrypt(credential.getPassword(), userEntity.getKey());
        if (!userEntity.getPassword().equals(password)) {
            log.info("{}用户名或密码错误", credential.getUserName());
            //TODO: 记入审计日志
        }
        return userEntity;
    }

}
