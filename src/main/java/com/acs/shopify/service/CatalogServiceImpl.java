package com.acs.shopify.service;

import com.acs.shopify.exception.EntityNotFoundException;
import com.acs.shopify.model.Catalog;
import com.acs.shopify.model.CatalogItem;
import com.acs.shopify.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    private CatalogRepository catalogRepository;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public Set<CatalogItem> getItems(Long catalogId) {

        return catalogRepository
                .findById(catalogId)
                .map(Catalog::getItems)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void addToCatalog(Long catalogId, CatalogItem catalogItem) {

        catalogRepository
                .findById(catalogId)
                .map(catalog -> {
                    catalog.addItem(catalogItem);
                    return catalogRepository.save(catalog);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Catalog save(Catalog catalog) {

        return catalogRepository
                .save(catalog);
    }
}
