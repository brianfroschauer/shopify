package com.acs.shopify.service;

import com.acs.shopify.model.Catalog;
import com.acs.shopify.model.CatalogItem;
import com.acs.shopify.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: brianfroschauer
 * Date: 2019-05-31
 */
@Service
public class CatalogServiceMemoryImpl implements CatalogService {

    private final List<Catalog> catalogList;

    public CatalogServiceMemoryImpl() {
        this.catalogList = new ArrayList<>();
    }

    @Override
    public Catalog save(Catalog catalog) {
        catalogList.add(catalog);
        return catalog;
    }

    @Override
    public Catalog addProduct(Catalog catalog, Product product, float price) {
        final CatalogItem catalogItem = new CatalogItem(product, price);
        catalog.addItem(catalogItem);
        catalogList.add(catalog);
        return catalog;
    }

    @Override
    public Catalog removeProduct(Catalog catalog, CatalogItem catalogItem) {
        catalog.removeItem(catalogItem);
        catalogList.add(catalog);
        return catalog;
    }
}
