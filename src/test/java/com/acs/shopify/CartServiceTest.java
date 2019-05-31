package com.acs.shopify;

import com.acs.shopify.model.*;
import com.acs.shopify.service.CartService;
import com.acs.shopify.service.CatalogService;
import com.acs.shopify.service.CustomerService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.Set;

/**
 * Author: brianfroschauer
 * Date: 2019-05-30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartServiceTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private CustomerService customerService;

    private Customer customer;

    @Before
    public void setup() {

        Customer customer = new Customer(
                "John",
                "Doe",
                "johndoe",
                "Password1");

        this.customer = customerService.save(customer);
    }

    @Test
    public void test001_cuandoSeCreaCarritoDebeEstarVacio() {

        Cart cart = new Cart(customer);

        cart = cartService.save(cart);

        assertNotNull(cart);

        assertEquals(0, cart.size());
    }

    @Test
    public void test002_cuandoAgregoUnCatalogItemAlCarritoMeDeberiaDevolverUnCarritoLleno() {

        Cart cart = new Cart(customer);

        cartService.save(cart);

        Product product = new Product("iPhone X", "All screen");

        Catalog catalog = new Catalog();

        final CatalogItem catalogItem = new CatalogItem(product, 1000F);

        catalog.addItem(catalogItem);

        catalogService.save(catalog);

        Cart filledCart = cartService.addToCart(cart, catalogItem);

        assert filledCart.size() > 0;
    }

    @Test
    public void test003_cuandoAgregoUnCatalogItemAlCarritoLaCantidadDebeSerUno() {

        Cart cart = new Cart(customer);

        cartService.save(cart);

        Product product = new Product("iPhone X", "All screen");

        Catalog catalog = new Catalog();

        final CatalogItem catalogItem = new CatalogItem(product, 1000F);

        catalog.addItem(catalogItem);

        catalogService.save(catalog);

        Cart filledCart = cartService.addToCart(cart, catalogItem);

        assertEquals(1, filledCart.size());
    }

    @Test
    public void test004_cuandoAgregoElMismoCatalogItemAlCarritoDebeAumentarElQuantity() {

        // setup
        Cart cart = new Cart(customer);

        cartService.save(cart);

        Product product1 = new Product("iPhone X", "All screen");
        Product product2 = new Product("iPhone X", "All screen");

        Catalog catalog = new Catalog();

        final CatalogItem catalogItem1 = new CatalogItem(product1, 1000F);
        final CatalogItem catalogItem2 = new CatalogItem(product2, 1000F);

        // add products to catalog
        catalog.addItem(catalogItem1);
        catalog.addItem(catalogItem2);

        catalogService.save(catalog);

        // add to cart
        cart = cartService.addToCart(cart, catalogItem1);
        cart = cartService.addToCart(cart, catalogItem2);

        assertEquals(1, cart.size());

        final Set<CartItem> cartItems = cart.getCartItems();

        final Iterator<CartItem> iterator = cartItems.iterator();

        assertEquals(2, iterator.next().getQuantity());
    }
}
