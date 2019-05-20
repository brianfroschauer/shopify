package com.acs.shopify.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(name = "total_items", nullable = false)
    private Integer totalItems = 0;

    @Column(name = "price", nullable = false)
    private Float price = 0F;

    @Column(name = "last_update", nullable = false)
    private LocalDate lastUpdate = LocalDate.now();

    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<CartItem> items;

    public void addItem(CartItem item) {
        items.add(item);
        lastUpdate = LocalDate.now();
        totalItems += item.getAmount();
        price += item.getCatalogItem().getPrice();
    }

    public void removeItem(CartItem item) {
        items.remove(item);
        lastUpdate = LocalDate.now();
        totalItems -= item.getAmount();
        price -= item.getCatalogItem().getPrice();
    }
}
