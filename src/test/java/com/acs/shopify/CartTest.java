package com.acs.shopify;

import com.acs.shopify.model.*;
import com.acs.shopify.service.CartService;
import com.acs.shopify.service.CatalogService;
import com.acs.shopify.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Author: brianfroschauer
 * Date: 2019-05-24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private CustomerService customerService;

    @Test
    public void test001_() {

        final Customer customer = new Customer(
                "Brian",
                "Froschauer",
                "brianfroschauer",
                "Password1");

        customerService.save(customer);

        final Cart saved = cartService.save(new Cart(customer));

        assertNotNull(saved);
    }

    @Test
    public void test002_() {

        final Customer customer = new Customer(
                "Brian",
                "Froschauer",
                "brianfroschauer",
                "Password1");

        customerService.save(customer);

        final Cart saved = cartService.save(new Cart(customer));

        assertEquals(0, saved.getItems().size());
    }

    @Test
    public void test003_() {

        final Customer customer = new Customer(
                "Brian",
                "Froschauer",
                "brianfroschauer",
                "Password1");

        final Catalog catalog = new Catalog();
        final Catalog saved = catalogService.save(catalog);

        assertNotNull(saved);

    }

    @Test
    public void test004_() {

        final Catalog catalog = new Catalog();
        final Catalog saved = catalogService.save(catalog);

        final CatalogItem catalogItem = new CatalogItem(
                new Product("iPhone X", "All screen"),
                1000F);

        saved.addItem(catalogItem);

        final Catalog updated = catalogService.save(catalog);

        assertEquals(1, updated.getItems().size());
    }


}
