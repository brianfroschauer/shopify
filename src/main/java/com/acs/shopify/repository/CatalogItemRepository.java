package com.acs.shopify.repository;

import com.acs.shopify.model.CatalogItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: brianfroschauer
 * Date: 2019-05-24
 */
public interface CatalogItemRepository extends JpaRepository<CatalogItem, Long> {

}
