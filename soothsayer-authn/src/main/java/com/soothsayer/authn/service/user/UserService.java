package com.soothsayer.authn.service.user;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient("${services.usermgt}")
public interface UserService {

    @GetMapping("/users/{id}/check")
    ResponseEntity<String> checkUser(@PathVariable("id")  String userId);
}
