package com.acs.shopify.population;

import com.acs.shopify.model.Catalog;
import com.acs.shopify.model.CatalogItem;
import com.acs.shopify.model.Product;
import com.acs.shopify.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
@Component
public class CatalogPopulation implements CommandLineRunner, Ordered {

    private final CatalogRepository catalogRepository;

    @Autowired
    public CatalogPopulation(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public void run(String... args) {

        final Catalog catalog = catalogRepository.save(new Catalog());

        CatalogItem catalogItem1 = new CatalogItem(new Product("Product 1", "Description 1"), 100F);
        CatalogItem catalogItem2 = new CatalogItem(new Product("Product 2", "Description 2"), 200F);
        CatalogItem catalogItem3 = new CatalogItem(new Product("Product 3", "Description 3"), 300F);
        CatalogItem catalogItem4 = new CatalogItem(new Product("Product 4", "Description 4"), 400F);
        CatalogItem catalogItem5 = new CatalogItem(new Product("Product 5", "Description 5"), 500F);

        catalog.addItem(catalogItem1);
        catalog.addItem(catalogItem2);
        catalog.addItem(catalogItem3);
        catalog.addItem(catalogItem4);
        catalog.addItem(catalogItem5);

        catalogRepository.save(catalog);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
