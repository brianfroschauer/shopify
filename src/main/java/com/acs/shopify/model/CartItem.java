package com.acs.shopify.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
@Data
@NoArgsConstructor
@Entity(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private CatalogItem catalogItem;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    public CartItem(CatalogItem catalogItem, Integer amount) {
        this.catalogItem = catalogItem;
        this.amount = amount;
    }
}
