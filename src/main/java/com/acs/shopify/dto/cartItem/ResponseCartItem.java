package com.acs.shopify.dto.cartItem;

import com.acs.shopify.dto.catalogItem.ResponseCatalogItem;
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
public class ResponseCartItem {

    private Long id;
    private ResponseCatalogItem responseCatalogItem;
    private Integer amount;
}
