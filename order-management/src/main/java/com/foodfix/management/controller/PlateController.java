package com.foodfix.management.controller;

import com.foodfix.management.model.Plate;
import com.foodfix.management.service.PlateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class PlateController {

    private final PlateService plateService;

    public PlateController(PlateService plateService) {
        this.plateService = plateService;
    }

    @GetMapping
    public ResponseEntity<List<Plate>> getAllPlates() {
        return ResponseEntity.ok(plateService.findAll());
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<Plate>> getFavoritePlates() {
        return ResponseEntity.ok(plateService.findFavorites());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Plate>> searchPlate(@RequestParam("name") String name) {
        return ResponseEntity.ok(plateService.findByNameContains(name));
    }

    @PostMapping
    public ResponseEntity<Plate> savePlate(@Valid @RequestBody Plate plate) {
        Plate newPlate = plateService.save(plate);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPlate.getId())
                .toUri();
        return ResponseEntity.created(location).body(newPlate);
    }

    @PutMapping
    public ResponseEntity<Plate> updatePlate(@Valid @RequestBody Plate plate) {
        return ResponseEntity.ok(plateService.save(plate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlate(@PathVariable int id) {
        plateService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
