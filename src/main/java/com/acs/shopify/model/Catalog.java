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
@Entity(name = "catalog")
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "total_items", nullable = false)
    private Integer totalItems = 0;

    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<CatalogItem> items;

    public void addItem(CatalogItem item) {
        items.add(item);
        totalItems += 1;
    }

    public void removeItem(CatalogItem item) {
        items.remove(item);
        totalItems -= 1;
    }
}
