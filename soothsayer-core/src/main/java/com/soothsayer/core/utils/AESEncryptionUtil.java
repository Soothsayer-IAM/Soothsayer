package com.soothsayer.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.util.Base64;

@Slf4j
public class AESEncryptionUtil {

    private static final String ALGORITHM = "AES/ECB/PKCS7Padding";
    private static final String KEY_ALGORITHM = "AES";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String encrypt(String src, String key) {
        String result = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
            SecretKeySpec keySpec = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            result = Base64.getEncoder().encodeToString(cipher.doFinal(src.getBytes("UTF-8")));
        } catch (Exception e) {
            log.error("AES加密出错");
            log.debug(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


    public static String decrypt(String src, String key) {
        String result = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
            SecretKeySpec keySpec = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decoded = cipher.doFinal(src.getBytes("UTF-8"));
            result = new String(decoded, "UTF-8");
        } catch (Exception e) {
            log.error("AES解密出错");
            e.printStackTrace();
        }
        return result;
    }

    public static String initKey() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM, "BC");
        kg.init(256);
        SecretKey secretKey = kg.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

}
