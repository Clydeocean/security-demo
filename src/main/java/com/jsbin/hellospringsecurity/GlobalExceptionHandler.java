package com.jsbin.hellospringsecurity;

import com.jsbin.hellospringsecurity.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthenticationException.class)
    public String handlerAuthenticationException(AuthenticationException e) {
        log.error("Authentication Exception e: ", e);
        return "Auth error";
    }

    @ExceptionHandler(Exception.class)
    public String handerException(Exception e) {
        log.error("Exception: ", e);
        return "Normal exception";
    }

    @ExceptionHandler(GlobalException.class)
    public Map<String, String> global(GlobalException e, HttpServletResponse response) {
        Map<String, String> data = new HashMap<>();
        data.put("msg", e.getMsg());
        response.setStatus(e.getHttpStatus().value());
        return data;
    }
}
