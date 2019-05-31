package com.acs.shopify.service;

import com.acs.shopify.model.Cart;
import com.acs.shopify.model.CatalogItem;
import org.springframework.stereotype.Service;

/**
 * Author: brianfroschauer
 * Date: 2019-05-30
 */
@Service
public interface CartService {

    /**
     *
     * @param cart
     * @return
     */
    Cart save(Cart cart);

    /**
     *
     * @param cart
     * @param catalogItem
     * @return
     */
    Cart addToCart(Cart cart, CatalogItem catalogItem);
}
