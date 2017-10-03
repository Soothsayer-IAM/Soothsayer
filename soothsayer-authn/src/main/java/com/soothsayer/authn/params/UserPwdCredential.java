package com.soothsayer.authn.params;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class UserPwdCredential {
    @NotBlank(message = "username 不能为空")
    @JsonProperty("username")
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String password;

    private String verifyCode;
}
