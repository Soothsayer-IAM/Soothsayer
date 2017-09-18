package com.soothsayer.core.utils;

import com.soothsayer.core.entities.user.UserEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;

public class CodecUtil {
    public static String generateSessionToken(UserEntity userEntity) {
        String src = String.format("%s-%s-%s-%s", DateTime.now().getMillis(), userEntity.getUserName(), userEntity.getMobile(), userEntity.getEmail());
        return DigestUtils.sha256Hex(src);
    }
}
