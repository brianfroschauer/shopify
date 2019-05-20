package com.acs.shopify.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestProduct {

    @Size(min = 2, max = 20)
    private String name;

    @Size(min = 2, max = 200)
    private String description;
}
