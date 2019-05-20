package com.acs.shopify.dto.cartItem;

import com.acs.shopify.model.CatalogItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCartItem {

    private CatalogItem catalogItem;

    @Min(1)
    private Integer amount;
}
