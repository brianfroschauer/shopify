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
     * Create a new catalog.
     *
     * @param catalog to be created.
     * @return the created catalog.
     */
    Catalog save(Catalog catalog);

    /**
     * Add a new product to the specified catalog.
     *
     * @param catalog to which the product is added.
     * @param product to be added to the catalog.
     * @param price of the product.
     * @return the updated catalog.
     */
    Catalog addProduct(Catalog catalog, Product product, float price);

    /**
     * Remove product from the specified catalog.
     *
     * @param catalog from which the item will be removed
     * @param catalogItem to be removed to the catalog.
     * @return the updated catalog.
     */
    Catalog removeProduct(Catalog catalog, CatalogItem catalogItem);
}
