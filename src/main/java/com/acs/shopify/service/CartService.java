package com.acs.shopify.service;

import com.acs.shopify.util.Callback;
import com.acs.shopify.model.Cart;
import com.acs.shopify.model.CartItem;
import com.acs.shopify.model.CatalogItem;
import org.springframework.stereotype.Service;

/**
 * Author: brianfroschauer
 * Date: 2019-05-30
 */
@Service
public interface CartService {

    /**
     * Create a new cart given an existing customer.
     *
     * @param cart to be created.
     * @return the created cart.
     */
    Cart save(Cart cart);

    /**
     * Add catalog item to cart.
     *
     * @param cart to which the item is added.
     * @param catalogItem to be added to the cart.
     * @return the updated cart.
     */
    Cart addToCart(Cart cart, CatalogItem catalogItem);

    /**
     * Remove item from the specified cart.
     *
     * @param cart from which the item will be removed.
     * @param cartItem to be removed to the cart.
     * @param callback to handle the response.
     */
    Cart remove(Cart cart, CartItem cartItem, Callback callback);
}
