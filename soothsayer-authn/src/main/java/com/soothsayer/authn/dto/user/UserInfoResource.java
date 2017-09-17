package com.soothsayer.authn.dto.user;

import lombok.Data;

@Data
public class UserInfoResource {
    private Status status;
    private String sessionToken;
    private String expiresAt;

    public enum Status {
        SUCCESS
    }
}
