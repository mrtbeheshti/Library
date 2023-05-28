package com.example.library.service;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;


public interface AuthenticationService {
    void login(HttpServletResponse response);

    void redirect(String code, HttpServletResponse response);
}
