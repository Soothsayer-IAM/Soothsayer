package com.soothsayer.authn.controller;

import com.soothsayer.authn.dto.user.UserInfoResource;
import com.soothsayer.authn.params.UserPwdCredential;
import com.soothsayer.authn.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/authn")
    public ResponseEntity<UserInfoResource> loginWithCredentials(@RequestBody @Validated UserPwdCredential userPwdCredential) {
        return new ResponseEntity<>(loginService.loginWithPwd(userPwdCredential), HttpStatus.OK);
    }
}
