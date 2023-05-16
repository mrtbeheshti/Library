package com.example.library.controller;

import com.example.library.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @GetMapping()
    public void login(HttpServletResponse response) {
        this.service.login(response);
    }

    @GetMapping("redirect")
    public void redirect(@RequestParam("code") String code,HttpServletResponse response) {
        this.service.redirect(code,response);
    }



}
