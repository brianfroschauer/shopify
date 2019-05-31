package com.acs.shopify.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: brianfroschauer
 * Date: 2019-05-24
 */
@Data
@NoArgsConstructor
@Entity(name = "catalog")
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_date")
    private LocalDate createdDate = LocalDate.now();

    @Column(name = "updated_date")
    private LocalDate updatedDate = LocalDate.now();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "catalog_id")
    private Set<CatalogItem> items = new HashSet<>();

    public void addItem(CatalogItem catalogItem) {
        items.add(catalogItem);
        updatedDate = LocalDate.now();
    }

    public void removeItem(CatalogItem catalogItem) {
        items.remove(catalogItem);
        updatedDate = LocalDate.now();
    }

    public int size() {
        return items.size();
    }
}
