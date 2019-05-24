package com.acs.shopify.controller;

import com.acs.shopify.dto.cart.ResponseCart;
import com.acs.shopify.dto.cartItem.ResponseCartItem;
import com.acs.shopify.dto.catalogItem.ResponseCatalogItem;
import com.acs.shopify.dto.product.ResponseProduct;
import com.acs.shopify.model.Cart;
import com.acs.shopify.model.CatalogItem;
import com.acs.shopify.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;
    private final ModelMapper mapper;

    public CartController(CartService cartService) {
        this.cartService = cartService;
        this.mapper = new ModelMapper();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<ResponseCart> getCartByCustomer(
            @PathVariable Long customerId) {

        final Cart cart = cartService.getCartByCustomer(customerId);

        final ResponseCart responseCart = mapper
                .map(cart, ResponseCart.class);

        return ResponseEntity.ok(responseCart);
    }

    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<ResponseCartItem>> getItems(
            @PathVariable Long cartId) {

        final List<ResponseCartItem> all = cartService.getItems(cartId).stream()
                .map(cartItem -> mapper.map(cartItem, ResponseCartItem.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(all);
    }

    @PostMapping("/{cartId}")
    public ResponseEntity<ResponseProduct> addToCart(
            @PathVariable Long cartId,
            @RequestBody @Valid ResponseCatalogItem responseCatalogItem) {

        final CatalogItem catalogItem = mapper
                .map(responseCatalogItem, CatalogItem.class);

        cartService.addToCart(cartId, catalogItem);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}/items/{itemId}")
    public ResponseEntity remove(
            @PathVariable Long cartId,
            @PathVariable Long itemId) {

        cartService.remove(cartId, itemId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity clear(
            @PathVariable Long cartId) {

        cartService.clear(cartId);

        return ResponseEntity.noContent().build();
    }
}
