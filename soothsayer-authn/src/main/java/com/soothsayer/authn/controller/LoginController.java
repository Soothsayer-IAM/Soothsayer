package com.soothsayer.authn.controller;

import com.soothsayer.authn.dto.user.UserInfoResource;
import com.soothsayer.authn.params.UserPwdCredential;
import com.soothsayer.authn.service.LoginService;
import com.soothsayer.core.entities.user.UserEntity;
import com.soothsayer.core.web.WebContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private WebContext context;

    @PostMapping("/authn")
    public ResponseEntity<UserInfoResource> loginWithCredentials(@RequestBody @Validated UserPwdCredential userPwdCredential) {
        return new ResponseEntity<>(loginService.loginWithPwd(userPwdCredential), HttpStatus.OK);
    }

    @GetMapping("/sessionRedirect")
    public void sessionRedirect(@RequestParam String token, @RequestParam String redirectUrl) throws IOException {
        UserEntity userEntity = loginService.exchangeUserInfo(token);
        if (userEntity != null) {
            context.getSession().setAttribute("LOGIN-USER", userEntity);
            context.redirect(redirectUrl);
        } else {
            context.redirect(String.format("/?fromUrl=%s", redirectUrl));
        }
    }
}
