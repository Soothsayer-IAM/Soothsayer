package com.soothsayer.audit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuditController {

    @GetMapping("/checkuser/{id}")
    public ResponseEntity<String> checkUser(@PathVariable String id) {
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
