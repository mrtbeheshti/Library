package com.example.library.service;

import javax.servlet.http.HttpServletResponse;


public interface AuthenticationService {
    void login(HttpServletResponse response);

    void redirect(String code, HttpServletResponse response);
}
