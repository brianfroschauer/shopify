package com.acs.shopify.service;

import com.acs.shopify.model.Catalog;
import com.acs.shopify.model.Product;
import org.springframework.stereotype.Service;

/**
 * Author: brianfroschauer
 * Date: 2019-05-24
 */
@Service
public interface CatalogService {

    Catalog save(Catalog catalog);

    Catalog addProduct(Catalog catalog, Product product, float price);
}
