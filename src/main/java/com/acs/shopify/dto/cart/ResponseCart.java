package com.acs.shopify.dto.cart;

import com.acs.shopify.dto.cartItem.ResponseCartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCart {

    private Long id;
    private Integer totalItems;
    private Float price;
    private LocalDate lastUpdate;
    private Set<ResponseCartItem> items;
}
