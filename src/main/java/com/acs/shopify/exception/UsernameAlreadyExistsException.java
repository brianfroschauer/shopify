package com.acs.shopify.exception;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException() {
        super("Username already exists");
    }
}
