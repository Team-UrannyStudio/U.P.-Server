package com.example.up_final.global.common.exception.error;

import org.springframework.http.HttpStatus;

public interface CustomErrorCode {
    HttpStatus getStatus();
    String getMessage();
}
