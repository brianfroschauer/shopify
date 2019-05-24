package com.acs.shopify.service;

import com.acs.shopify.model.Cart;
import com.acs.shopify.model.CartItem;
import com.acs.shopify.model.CatalogItem;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
@Service
public interface CartService {

    /**
     * Get cart with provided id.
     *
     * @param cartId id of the cart.
     * @return the cart.
     */
    Cart getCart(Long cartId);

    /**
     * Get cart y customer with provided id.
     *
     * @param customerId id of the customer's cart.
     * @return the cart.
     */
    Cart getCartByCustomer(Long customerId);

    /**
     * Get all items from the cart with provided id.
     *
     * @param cartId id of the cart.
     * @return all items from the cart.
     */
    Set<CartItem> getItems(Long cartId);

    /**
     * Add product to cart.
     *
     * @param cartId id of the cart.
     * @param catalogItem to be added to cart.
     */
    void addToCart(Long cartId, CatalogItem catalogItem);

    /**
     * Save a new cart.
     *
     * @param cart to be saved.
     * @return the created cart.
     */
    Cart save(Cart cart);

    /**
     * Remove item from cart.
     *
     * @param cartId id of the cart.
     * @param itemId id of the item to be removed.
     */
    void remove(Long cartId, Long itemId);

    /**
     * Remove all items of a cart with provided id.
     *
     * @param cartId id of the cart.
     */
    void clear(Long cartId);
}
