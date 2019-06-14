package com.acs.shopify;

import com.acs.shopify.model.Catalog;
import com.acs.shopify.model.CatalogItem;
import com.acs.shopify.model.Product;
import com.acs.shopify.repository.CatalogRepository;
import com.acs.shopify.repository.ProductRepository;
import com.acs.shopify.service.CatalogServiceMemoryImpl;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author: brianfroschauer
 * Date: 2019-05-24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
@TestPropertySource("/application.properties")
public class CatalogServiceTest {

    @Autowired
    private CatalogServiceMemoryImpl catalogService;

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void test001_aCatalogWhenCreatedShouldBeEmpty() {

        Catalog catalog = new Catalog();

        catalog = catalogService.save(catalog);

        assertNotNull(catalog);

        assertEquals(0, catalog.size());
    }

    @Test
    public void test002_aCatalogWhenAddedProductShouldReturnAnUpdatedCatalog() {

        Catalog catalog = new Catalog();

        Product product = new Product("iPhone X", "All screen");

        catalog = catalogService.addProduct(catalog, product, 1000F);

        assertNotNull(catalog);

        assertEquals(1, catalog.size());
    }

    @Test
    public void test003_aCatalogWhenAddedRepeatedProductShouldNotDoModifications() {

        Catalog catalog = new Catalog();

        Product product1 = new Product("iPhone X", "All screen");

        catalog = catalogService.addProduct(catalog, product1, 1000F);

        catalog = catalogService.addProduct(catalog, product1, 1000F);

        assertEquals(1, catalog.size());
    }

    @Test
    public void test004_aCatalogWhenAddedTwoProductsShouldIncreaseTheSize() {

        Catalog catalog = new Catalog();

        Product product1 = new Product("iPhone X", "All screen");

        Product product2 = new Product("iPhone 8 Plus", "Dual camera");

        catalog = catalogService.addProduct(catalog, product1, 1000F);

        catalog = catalogService.addProduct(catalog, product2, 800F);

        assertEquals(2, catalog.size());
    }

    @Test
    public void test005_aCatalogWhenAddedTheSameProductWithDifferentPriceShouldBeUpdated() {

        Catalog catalog = new Catalog();

        Product product1 = new Product("iPhone X", "All screen");

        catalog = catalogService.addProduct(catalog, product1, 1000F);

        catalog = catalogService.addProduct(catalog, product1, 800F);

        assertEquals(1, catalog.size());
    }

    @Test
    public void test006_aCatalogWhenAddedTwoDifferentProductsWithTheSameCharacteristicsShouldUpdateTheOriginalProduct() {

        Catalog catalog = new Catalog();

        Product product1 = new Product("iPhone X", "All screen");

        Product product2 = new Product("iPhone X", "All screen");

        catalog = catalogService.addProduct(catalog, product1, 1000F);

        catalog = catalogService.addProduct(catalog, product2, 800F);

        assertEquals(1, catalog.size());
    }

    @Test
    public void test007_aCatalogWhenRemovedAProductShouldReturnAnUpdatedCatalogWithoutTheProduct() {

        Catalog catalog = new Catalog();

        Product product1 = new Product("iPhone X", "All screen");

        catalog = catalogService.addProduct(catalog, product1, 1000F);

        catalog = catalogService.removeProduct(catalog, new CatalogItem(product1, 1000F));

        assertEquals(0, catalog.size());
    }

    @After
    public void tearDown() {
        catalogRepository.deleteAll();
        productRepository.deleteAll();
    }
}
