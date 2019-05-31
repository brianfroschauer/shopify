package com.acs.shopify.service;

import com.acs.shopify.util.Callback;
import com.acs.shopify.model.Cart;
import com.acs.shopify.model.CartItem;
import com.acs.shopify.model.CatalogItem;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: brianfroschauer
 * Date: 2019-05-31
 */
@Service
public class CartServiceMemoryImpl implements CartService {

    private final Set<Cart> carts;

    public CartServiceMemoryImpl() {
        this.carts = new HashSet<>();
    }

    @Override
    public Cart save(Cart cart) {
        carts.add(cart);
        return cart;
    }

    @Override
    public Cart addToCart(Cart cart, CatalogItem catalogItem) {
        cart.add(catalogItem);
        return cart;
    }

    @Override
    public Cart remove(Cart cart, CartItem cartItem, Callback callback) {
        cart.removeItem(cartItem);
        return cart;
    }
}
