package com.acs.shopify.exception;

import lombok.Getter;

import java.util.Date;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
@Getter
class ErrorInfo {

    private final Date timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;

    ErrorInfo(int status, String error, String message, String path) {
        this.timestamp = new Date();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
