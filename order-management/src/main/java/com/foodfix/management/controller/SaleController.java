package com.foodfix.management.controller;

import com.foodfix.management.dto.OrderDTO;
import com.foodfix.management.model.Sale;
import com.foodfix.management.service.SaleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<Page<Sale>> getAllSales(Pageable pageable) {
        return ResponseEntity.ok(saleService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Sale> saleOrder(@Valid @RequestBody OrderDTO dto) throws Exception {
        Sale sale = saleService.register(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(sale.getId())
                .toUri();
        return ResponseEntity.created(location).body(sale);
    }

}
