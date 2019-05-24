package com.acs.shopify.service;

import com.acs.shopify.exception.EntityNotFoundException;
import com.acs.shopify.model.Cart;
import com.acs.shopify.model.CartItem;
import com.acs.shopify.model.CatalogItem;
import com.acs.shopify.repository.CartItemRepository;
import com.acs.shopify.repository.CartRepository;
import com.acs.shopify.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private CatalogRepository catalogRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository,
                           CartItemRepository cartItemRepository,
                           CatalogRepository catalogRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.catalogRepository = catalogRepository;
    }

    @Override
    public Cart getCart(Long cartId) {

        return cartRepository
                .findById(cartId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Cart getCartByCustomer(Long customerId) {

        return cartRepository
                .findByCustomerId(customerId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Set<CartItem> getItems(Long cartId) {
        return cartRepository
                .findById(cartId)
                .map(Cart::getItems)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void addToCart(Long cartId, CatalogItem catalogItem) {

        validateCatalogItem(catalogItem);

        cartRepository
                .findById(cartId)
                .map(cart -> {
                    cart.addItem(new CartItem(catalogItem));
                    return cartRepository.save(cart);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Cart save(Cart cart) {

        return cartRepository
                .save(cart);
    }

    @Override
    public void remove(Long cartId, Long itemId) {

        final CartItem cartItem = cartItemRepository
                .findCartItemByIdAndCartId(itemId, cartId)
                .orElseThrow(EntityNotFoundException::new);


        final Cart cart = cartRepository
                .findById(cartId)
                .orElseThrow(EntityNotFoundException::new);

        cart.removeItem(cartItem);

        cartRepository.save(cart);
    }

    @Override
    public void clear(Long cartId) {

        cartRepository
                .findById(cartId)
                .ifPresent(Cart::clear);
    }

    private void validateCatalogItem(CatalogItem catalogItem) {

        catalogRepository
                .findById(catalogItem.getId())
                .orElseThrow(EntityNotFoundException::new);
    }
}
