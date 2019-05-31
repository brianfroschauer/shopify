package com.acs.shopify.service;

import com.acs.shopify.model.Catalog;
import com.acs.shopify.model.CatalogItem;
import com.acs.shopify.model.Product;
import org.springframework.stereotype.Service;

/**
 * Author: brianfroschauer
 * Date: 2019-05-24
 */
@Service
public interface CatalogService {

    /**
     *
     * @param catalog
     * @return
     */
    Catalog save(Catalog catalog);

    /**
     *
     * @param catalog
     * @param product
     * @param price
     * @return
     */
    Catalog addProduct(Catalog catalog, Product product, float price);

    /**
     *
     * @param catalog
     * @param catalogItem
     * @return
     */
    Catalog removeProduct(Catalog catalog, CatalogItem catalogItem);
}
