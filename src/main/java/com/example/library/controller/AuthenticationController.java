package com.example.library.controller;

import com.example.library.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

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
