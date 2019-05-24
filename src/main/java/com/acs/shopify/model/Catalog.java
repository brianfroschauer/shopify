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
@Entity(name = "catalog")
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_created")
    private LocalDate dateCreated = LocalDate.now();

    @Column(name = "date_updated")
    private LocalDate dateUpdated = LocalDate.now();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "catalog_id")
    private Set<CatalogItem> items = new HashSet<>();

    public void addItem(CatalogItem catalogItem) {
        items.add(catalogItem);
        dateUpdated = LocalDate.now();
    }

    public void removeItem(CatalogItem catalogItem) {
        items.remove(catalogItem);
        dateUpdated = LocalDate.now();
    }
}
