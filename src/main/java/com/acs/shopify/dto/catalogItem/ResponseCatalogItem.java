package com.acs.shopify.dto.catalogItem;

import com.acs.shopify.dto.product.ResponseProduct;
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
public class ResponseCatalogItem {

    private Long id;
    private ResponseProduct product;
    private Float price;
}
