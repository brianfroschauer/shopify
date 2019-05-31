package com.acs.shopify.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Author: brianfroschauer
 * Date: 2019-05-30
 */
@Data
@NoArgsConstructor
@Entity(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_date")
    private LocalDate createdDate = LocalDate.now();

    @Column(name = "updated_date")
    private LocalDate updatedDate = LocalDate.now();

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private List<CartItem> cartItems = new ArrayList<>();

    public Cart(Customer customer) {
        this.customer = customer;
    }

    public int size() {
        return cartItems.size();
    }

    public void add(CatalogItem catalogItem) {
        final CartItem cartItem = new CartItem(catalogItem);

        if (cartItems.contains(cartItem)) {
            final CartItem item = cartItems.get(cartItems.indexOf(cartItem));
            item.setQuantity(cartItem.getQuantity() + 1);
        } else {
            cartItems.add(cartItem);
        }
    }

    public void removeItem(CartItem cartItem) {
        cartItems.remove(cartItem);
    }
}
