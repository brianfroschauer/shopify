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
@Entity(name = "catalog_item")
public class CatalogItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "price")
    private Float price;

    public CatalogItem(Product product, Float price) {
        this.product = product;
        this.price = price;
    }
}
