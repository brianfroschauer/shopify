package com.acs.shopify.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCustomer {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
}
