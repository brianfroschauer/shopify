package com.acs.shopify.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
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
    @Column(name = "id")
    private Long id;

    @Column(name = "date_created")
    private LocalDate dateCreated = LocalDate.now();

    @Column(name = "date_updated")
    private LocalDate dateUpdated = LocalDate.now();

    @Column(name = "price")
    private Float price = 0F;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<CartItem> items = new HashSet<>();

    public Cart(Customer customer) {
        this.customer = customer;
    }

    public void addItem(CartItem item) {
        items.add(item);
        dateUpdated = LocalDate.now();
        price += item.getCatalogItem().getPrice();
    }

    public void removeItem(CartItem item) {
        items.remove(item);
        dateUpdated = LocalDate.now();
        price -= item.getCatalogItem().getPrice();
    }

    public void clear() {
        items.clear();
    }
}
