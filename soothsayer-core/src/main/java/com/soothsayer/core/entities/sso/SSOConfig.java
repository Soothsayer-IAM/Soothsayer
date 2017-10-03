package com.soothsayer.core.entities.sso;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document
public class SSOConfig {

    private SSOType ssoType;
    private Map<String, String> ssoConfigContent;

    public enum SSOType {
        CAS, SAML, OAUTH2, OPENID
    }
}
