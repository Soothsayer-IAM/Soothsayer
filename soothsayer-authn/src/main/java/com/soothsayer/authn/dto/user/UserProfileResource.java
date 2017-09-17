package com.soothsayer.authn.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfileResource {
    private String userName;
    private String disPlayName;
    private String email;
}
