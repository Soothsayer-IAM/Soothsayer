package com.soothsayer.authn.params;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class UserPwdCredential {
    @NotBlank(message = "userName 不能为空")
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String password;

    private String verifyCode;
}
