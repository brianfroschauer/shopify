package com.acs.shopify.service;

import com.acs.shopify.model.Catalog;
import com.acs.shopify.model.CatalogItem;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
@Service
public interface CatalogService {

    /**
     * Get all items from the catalog with provided id.
     *
     * @param catalogId id of the catalog.
     * @return all items from the catalog.
     */
    Set<CatalogItem> getItems(Long catalogId);

    /**
     * Add product to catalog.
     *
     * @param catalogId id of the catalog.
     * @param catalogItem to be added to the catalog.
     */
    void addToCatalog(Long catalogId, CatalogItem catalogItem);

    /**
     * Save catalog.
     *
     * @param catalog to be saved.
     * @return the created catalog.
     */
    Catalog save(Catalog catalog);
}
