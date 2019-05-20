package com.acs.shopify.service;

import com.acs.shopify.model.Cart;
import com.acs.shopify.model.CartItem;
import com.acs.shopify.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * Get all items from the cart with provided id.
     *
     * @param cartId id of the cart.
     * @return all items from the cart.
     */
    List<CartItem> getItems(Long cartId);

    /**
     * Add product to cart.
     *
     * @param product to be added to cart.
     */
    void addToCart(Product product);

    /**
     * Add product to cart with an amount.
     *
     * @param product to be added to cart.
     * @param amount of the product.
     */
    void addToCart(Product product, Integer amount);

    /**
     * Remove item from cart.
     *
     * @param cartId id of the cart.
     * @param itemId id of the item to be removed.
     */
    void remove(Long cartId, Long itemId);

    /**
     * Remove {@param amount} items from cart.
     *
     * @param cartId id of the cart.
     * @param itemId id of the item to be removed.
     * @param amount of items to be removed.
     */
    void remove(Long cartId, Long itemId, Integer amount);

    /**
     * Remove all items of a cart with provided id.
     *
     * @param cartId id of the cart.
     */
    void clear(Long cartId);
}
