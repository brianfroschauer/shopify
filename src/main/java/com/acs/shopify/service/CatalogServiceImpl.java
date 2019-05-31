package com.acs.shopify.service;

import com.acs.shopify.model.Catalog;
import com.acs.shopify.model.CatalogItem;
import com.acs.shopify.model.Product;
import com.acs.shopify.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: brianfroschauer
 * Date: 2019-05-24
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public Catalog save(Catalog catalog) {
        return catalogRepository.save(catalog);
    }

    @Override
    public Catalog addProduct(Catalog catalog, Product product, float price) {

        final CatalogItem catalogItem = new CatalogItem(product, price);

        catalog.addItem(catalogItem);

        return catalogRepository.save(catalog);
    }

    @Override
    public Catalog removeProduct(Catalog catalog, CatalogItem catalogItem) {

        catalog.removeItem(catalogItem);

        return catalogRepository.save(catalog);
    }
}
