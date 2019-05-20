package com.acs.shopify.service;

import com.acs.shopify.model.Cart;
import com.acs.shopify.model.CartItem;
import com.acs.shopify.model.CatalogItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
@Service
public class CartServiceImpl implements CartService {

    @Override
    public Cart getCart(Long cartId) {
        return null;
    }

    @Override
    public List<CartItem> getItems(Long cartId) {
        return null;
    }

    @Override
    public void addToCart(Long cartId, CatalogItem catalogItem) {

    }

    @Override
    public void remove(Long cartId, Long itemId) {

    }

    @Override
    public void clear(Long cartId) {

    }
}
