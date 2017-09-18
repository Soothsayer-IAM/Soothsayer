package com.soothsayer.authn.service.authn.impl;

import com.soothsayer.authn.dao.user.UserDAO;
import com.soothsayer.authn.params.UserPwdCredential;
import com.soothsayer.authn.service.authn.Authenticator;
import com.soothsayer.core.entities.user.UserEntity;
import com.soothsayer.core.utils.AESEncryptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PwdAuthenticator implements Authenticator<UserPwdCredential> {

    private static final Logger logger = LoggerFactory.getLogger(PwdAuthenticator.class);

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserEntity authn(UserPwdCredential credential) {
        UserEntity userEntity = userDAO.findByUserName(credential.getUserName());
        if (userEntity == null) {
            logger.info("{}用户名或密码错误", credential.getUserName());
            //TODO: 记入审计日志
        }
        String password = AESEncryptionUtil.encrypt(credential.getPassword(), userEntity.getKey());
        if (!userEntity.getPassword().equals(password)) {
            logger.info("{}用户名或密码错误", credential.getUserName());
            //TODO: 记入审计日志
        }
        return userEntity;
    }

}
