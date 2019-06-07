package com.acs.shopify;

import com.acs.shopify.model.*;
import com.acs.shopify.repository.CartRepository;
import com.acs.shopify.repository.CatalogRepository;
import com.acs.shopify.repository.CustomerRepository;
import com.acs.shopify.service.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

/**
 * Author: brianfroschauer
 * Date: 2019-05-30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartServiceDatabaseImpl cartService;

    @Autowired
    private CatalogServiceDatabaseImpl catalogService;

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
    public void test001_aCartWhenCreatedShouldBeEmpty() {

        Cart cart = new Cart(customer);

        cart = cartService.save(cart);

        assertNotNull(cart);

        assertEquals(0, cart.size());
    }

    @Test
    public void test002_aCartWhenAddCatalogItemShouldReturnFullCart() {

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
    public void test003_aCartWhenAddCatalogItemShouldHaveSizeEqualsOne() {

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
    public void test004_aCartWhenAddSameProductTwoTimesShouldIncreaseQuantity() {

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

        final List<CartItem> cartItems = cart.getCartItems();

        assertEquals(2, cartItems.get(0).getQuantity());
    }

    @Test
    public void test005_aCartWhenAddSameProductTwoTimesShouldIncreaseQuantity() {

        // setup
        Cart cart = new Cart(customer);

        cartService.save(cart);

        Product product1 = new Product("iPhone X", "All screen");
        Product product2 = new Product("iPhone 8", "Old phone");

        Catalog catalog = new Catalog();

        final CatalogItem catalogItem1 = new CatalogItem(product1, 1000F);
        final CatalogItem catalogItem2 = new CatalogItem(product2, 800F);

        // add products to catalog
        catalog.addItem(catalogItem1);
        catalog.addItem(catalogItem2);

        catalogService.save(catalog);

        // add to cart
        cart = cartService.addToCart(cart, catalogItem1);
        cart = cartService.addToCart(cart, catalogItem2);
        cart = cartService.addToCart(cart, catalogItem2);

        assertEquals(2, cart.size());
        assertEquals(1, cart.getCartItems().get(0).getQuantity());
        assertEquals(2, cart.getCartItems().get(1).getQuantity());
    }

    @Test
    public void test006_aCartWhenDeleteUniqueCartItemShouldHaveSizeEqualsZero() {

        Cart cart = new Cart(customer);

        cartService.save(cart);

        Product product = new Product("iPhone X", "All screen");

        Catalog catalog = new Catalog();

        final CatalogItem catalogItem = new CatalogItem(product, 1000F);

        catalog.addItem(catalogItem);

        catalogService.save(catalog);

        Cart filledCart = cartService.addToCart(cart, catalogItem);

        cartService.remove(filledCart, new CartItem(catalogItem), () -> {});

        assertEquals(0, filledCart.size());
    }

    @Test
    public void test007_aCartWhenDeleteCartItemWithQuantityEqualsTwoShouldHaveSizeEqualsZero() {

        Cart cart = new Cart(customer);

        cartService.save(cart);

        Product product = new Product("iPhone X", "All screen");

        Catalog catalog = new Catalog();

        final CatalogItem catalogItem = new CatalogItem(product, 1000F);

        catalog.addItem(catalogItem);

        catalogService.save(catalog);

        cart = cartService.addToCart(cart, catalogItem);
        cart = cartService.addToCart(cart, catalogItem);

        cart = cartService.remove(cart, cart.getCartItems().get(0), () -> {});

        assertEquals(0, cart.size());
    }

    @After
    public void tearDown() {
        cartRepository.deleteAll();
        catalogRepository.deleteAll();
        customerRepository.deleteAll();
    }
}
