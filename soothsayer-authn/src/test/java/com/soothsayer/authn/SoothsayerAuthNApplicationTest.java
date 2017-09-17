package com.soothsayer.authn;

import com.soothsayer.authn.dao.user.UserDAO;
import coms.soothsayer.core.entities.user.UserEntity;
import coms.soothsayer.core.utils.AESEncryptionUtil;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SoothsayerAuthNApplicationTest {
    @Autowired
    private UserDAO userDAO;

    @Before
    public void initUser() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("shh.gao");
        userEntity.setDisplayName("Rory");
        userEntity.setEmail("roryblucky@gmail.com");
        userEntity.setCreateDate(DateTime.now());
        userEntity.setMobile("18182605713");
        String key = AESEncryptionUtil.initKey();
        userEntity.setKey(key);
        userEntity.setPassword(AESEncryptionUtil.encrypt("abc@123", userEntity.getKey()));
        userEntity.setActive(true);
        userDAO.save(userEntity);
    }
    @Test
    public void test() {
        System.out.println("aaaa");
    }
}
