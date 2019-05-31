package com.acs.shopify;

import com.acs.shopify.service.CartServiceDatabaseImpl;
import com.acs.shopify.service.CatalogServiceDatabaseImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CartServiceDatabaseImplTest {

    @Autowired
    private CartServiceDatabaseImpl cartService;

    @Autowired
    private CatalogServiceDatabaseImpl catalogService;

    @Test
    public void execute() {

        final CartServiceTest cartServiceTest =
                new CartServiceTest(cartService, catalogService);

        cartServiceTest.test001_aCartWhenCreatedShouldBeEmpty();
        cartServiceTest.test002_aCartWhenAddCatalogItemShouldReturnFullCart();
        cartServiceTest.test003_aCartWhenAddCatalogItemShouldHaveSizeEqualsOne();
        cartServiceTest.test004_aCartWhenAddSameProductTwoTimesShouldIncreaseQuantity();
        cartServiceTest.test005_aCartWhenAddSameProductTwoTimesShouldIncreaseQuantity();
        cartServiceTest.test006_aCartWhenDeleteUniqueCartItemShouldHaveSizeEqualsZero();
        cartServiceTest.test007_aCartWhenDeleteCartItemWithQuantityEqualsTwoShouldHaveSizeEqualsZero();
    }
}
