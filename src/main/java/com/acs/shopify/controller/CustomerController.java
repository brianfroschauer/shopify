package com.acs.shopify.controller;

import com.acs.shopify.dto.customer.RequestCustomer;
import com.acs.shopify.dto.customer.ResponseCustomer;
import com.acs.shopify.model.Customer;
import com.acs.shopify.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final ModelMapper mapper;

    @Autowired
    private CustomerController(CustomerService customerService) {

        this.customerService = customerService;
        this.mapper = new ModelMapper();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCustomer> getOne(@PathVariable Long id) {

        final Customer one = customerService.findOne(id);

        final ResponseCustomer responseCustomer = mapper
                .map(one, ResponseCustomer.class);

        return ResponseEntity.ok(responseCustomer);
    }

    @GetMapping
    public ResponseEntity<List<ResponseCustomer>> getAll() {
        final List<ResponseCustomer> all = customerService.findAll().stream()
                .map(customer -> mapper.map(customer, ResponseCustomer.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(all);
    }

    @PostMapping
    public ResponseEntity<ResponseCustomer> save(@RequestBody @Valid RequestCustomer requestCustomer) {

        final Customer customer = mapper.map(requestCustomer, Customer.class);
        final Customer saved = customerService.save(customer);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();

        final ResponseCustomer responseCustomer = mapper
                .map(saved, ResponseCustomer.class);

        return ResponseEntity.created(location).body(responseCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCustomer> update(@PathVariable Long id,
                                                   @RequestBody @Valid RequestCustomer requestCustomer) {

        final Customer customer = mapper.map(requestCustomer, Customer.class);
        final Customer updated = customerService.update(id, customer);

        final ResponseCustomer responseCustomer = mapper
                .map(updated, ResponseCustomer.class);

        return ResponseEntity.ok(responseCustomer);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseCustomer> delete(@PathVariable Long id) {

        customerService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
