package com.acs.shopify.dto.catalogItem;

import com.acs.shopify.dto.product.RequestProduct;
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
public class RequestCatalogItem {

    private RequestProduct requestProduct;
    private Float price;
}
