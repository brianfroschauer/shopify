package com.acs.shopify.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
@Data
@NoArgsConstructor
@Entity(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<CartItem> items;

    public void addItem(CartItem item) {
        items.add(item);
    }

    public void removeItem(CartItem item) {
        items.remove(item);
    }
}
