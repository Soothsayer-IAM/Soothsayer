package com.soothsayer.authn.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserAPIController extends BaseAPIController {

    @GetMapping("/user")
    public Principal user(Principal currentUser) {
        return currentUser;
    }

}
