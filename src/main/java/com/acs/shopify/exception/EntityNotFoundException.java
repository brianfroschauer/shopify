package com.acs.shopify.exception;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
        super("Entity not found");
    }
}
