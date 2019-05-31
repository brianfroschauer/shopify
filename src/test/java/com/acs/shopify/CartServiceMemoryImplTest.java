package com.acs.shopify;

import com.acs.shopify.service.CartServiceMemoryImpl;
import com.acs.shopify.service.CatalogServiceMemoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author: brianfroschauer
 * Date: 2019-05-31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartServiceMemoryImplTest {

    @Test
    public void execute() {

        final CartServiceTest cartServiceTest =
                new CartServiceTest(new CartServiceMemoryImpl(), new CatalogServiceMemoryImpl());

        cartServiceTest.test001_aCartWhenCreatedShouldBeEmpty();
        cartServiceTest.test002_aCartWhenAddCatalogItemShouldReturnFullCart();
        cartServiceTest.test003_aCartWhenAddCatalogItemShouldHaveSizeEqualsOne();
        cartServiceTest.test004_aCartWhenAddSameProductTwoTimesShouldIncreaseQuantity();
        cartServiceTest.test005_aCartWhenAddSameProductTwoTimesShouldIncreaseQuantity();
        cartServiceTest.test006_aCartWhenDeleteUniqueCartItemShouldHaveSizeEqualsZero();

    }
}
