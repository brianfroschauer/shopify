package com.acs.shopify.controller;

import com.acs.shopify.dto.catalogItem.RequestCatalogItem;
import com.acs.shopify.dto.catalogItem.ResponseCatalogItem;
import com.acs.shopify.dto.product.ResponseProduct;
import com.acs.shopify.model.CatalogItem;
import com.acs.shopify.model.Product;
import com.acs.shopify.service.CatalogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private CatalogService catalogService;
    private final ModelMapper mapper;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
        this.mapper = new ModelMapper();
    }

    @GetMapping("/{catalogId}/items")
    public ResponseEntity<List<ResponseCatalogItem>> getItems(
            @PathVariable Long catalogId) {

        final List<ResponseCatalogItem> all = catalogService.getItems(catalogId).stream()
                .map(catalogItem -> mapper.map(catalogItem, ResponseCatalogItem.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(all);
    }

    @PostMapping("/{catalogId}")
    public ResponseEntity<ResponseProduct> addToCatalog(
            @PathVariable Long catalogId,
            @RequestBody @Valid RequestCatalogItem responseCatalogItem) {

        final CatalogItem catalogItem = mapper
                .map(responseCatalogItem, CatalogItem.class);

        catalogService.addToCatalog(catalogId, catalogItem);

        return ResponseEntity.noContent().build();
    }

}
