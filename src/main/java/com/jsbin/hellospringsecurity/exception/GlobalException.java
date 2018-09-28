package com.jsbin.hellospringsecurity.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GlobalException extends RuntimeException {
    private String msg;
    private HttpStatus httpStatus;
    private List<String> params;

    public GlobalException(HttpStatus httpStatus, String msg) {
        this.httpStatus = httpStatus;
        this.msg = msg;
    }
}
