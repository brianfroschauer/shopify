package com.acs.shopify.dto.product;

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
public class ResponseProduct {

    private Long id;
    private String name;
    private String description;
}
