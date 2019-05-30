package com.acs.shopify;

import com.acs.shopify.model.Catalog;
import com.acs.shopify.model.Product;
import com.acs.shopify.service.CatalogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author: brianfroschauer
 * Date: 2019-05-24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CatalogServiceTest {

    @Autowired
    private CatalogService catalogService;

    @Test
    public void cuandoCreoUnCatalogoEsteNoDebeContenerItems() {

        Catalog catalog = new Catalog();

        catalog = catalogService.save(catalog);

        assertNotNull(catalog);

        assertEquals(0, catalog.size());
    }

    @Test
    public void cuandoAgregoUnProductoAlCatalogoDeberiaDevolvermeElCatalogItemCreado() {

        Catalog catalog = new Catalog();

        Product product = new Product("iPhone X", "All screen");

        catalog = catalogService.addProduct(catalog, product, 1000F);

        assertNotNull(catalog);

        assertNotNull(catalog.getId());

        assertEquals(1, catalog.size());
    }

    @Test
    public void cuandoAgregoDosProductosIgualesNoDeberíaAgregarDosProductosAlCatalogo() {

        Catalog catalog = new Catalog();

        Product product1 = new Product("iPhone X", "All screen");

        catalog = catalogService.addProduct(catalog, product1, 1000F);

        catalog = catalogService.addProduct(catalog, product1, 1000F);

        assertEquals(1, catalog.size());
    }

    @Test
    public void cuandoAgregoDosProductosDistintosElSizeDelCatalogoDebeSer2() {

        Catalog catalog = new Catalog();

        Product product1 = new Product("iPhone X", "All screen");

        Product product2 = new Product("iPhone 8 Plus", "Dual camera");

        catalog = catalogService.addProduct(catalog, product1, 1000F);

        catalog = catalogService.addProduct(catalog, product2, 800F);

        assertEquals(2, catalog.size());
    }

    @Test
    public void cuandoAgregoElMismoProductoConOtroPrecioElProductoDebeSerActualizado() {

        Catalog catalog = new Catalog();

        Product product1 = new Product("iPhone X", "All screen");

        Product product2 = new Product("iPhone X", "All screen");

        catalog = catalogService.addProduct(catalog, product1, 1000F);

        catalog = catalogService.addProduct(catalog, product1, 800F);


        assertEquals(1, catalog.size());
    }
}
