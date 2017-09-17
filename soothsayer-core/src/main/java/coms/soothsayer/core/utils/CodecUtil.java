package coms.soothsayer.core.utils;

import coms.soothsayer.core.entities.user.UserEntity;
import org.apache.commons.codec.digest.DigestUtils;

public class CodecUtil {
    public static String generateSessionToken(UserEntity userEntity) {
        String src = String.format("%s-%s-%s", userEntity.getUserName(), userEntity.getMobile(), userEntity.getEmail());
        return DigestUtils.sha256Hex(src);
    }
}
