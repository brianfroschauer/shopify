package com.acs.shopify.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Set<CartItem> cartItems = new HashSet<>();

    public Cart(Customer customer) {
        this.customer = customer;
    }

    public int size() {
        return cartItems.size();
    }

    public void add(CatalogItem catalogItem) {
        final CartItem cartItem = new CartItem(catalogItem);

        if (cartItems.contains(cartItem)) {
            final Iterator<CartItem> iterator = cartItems.iterator();
            final CartItem next = iterator.next();
            next.setQuantity(cartItem.getQuantity() + 1);
        } else {
            cartItems.add(cartItem);
        }
    }
}
