package com.acs.shopify;

import com.acs.shopify.model.Cart;
import com.acs.shopify.service.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void test001_cuandoSeCreaCarritoDebeEstarVacio() {

        Cart cart = new Cart();

        cart = cartService.save(cart);

        assertNotNull(cart);

        assertEquals(0, cart.size());
    }
}
