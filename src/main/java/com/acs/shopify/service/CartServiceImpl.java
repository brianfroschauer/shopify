package com.acs.shopify.service;

import com.acs.shopify.util.Callback;
import com.acs.shopify.model.Cart;
import com.acs.shopify.model.CartItem;
import com.acs.shopify.model.CatalogItem;
import com.acs.shopify.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: brianfroschauer
 * Date: 2019-05-30
 */
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart save(Cart cart) {

        return cartRepository.save(cart);
    }

    @Override
    public Cart addToCart(Cart cart, CatalogItem catalogItem) {

        cart.add(catalogItem);

        return cartRepository.save(cart);
    }

    @Override
    public Cart remove(Cart cart, CartItem cartItem, Callback callback) {

        cart.removeItem(cartItem);

        return cartRepository.save(cart);
    }
}
