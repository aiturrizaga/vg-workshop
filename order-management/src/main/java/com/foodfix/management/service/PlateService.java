package com.foodfix.management.service;

import com.foodfix.management.model.Plate;
import com.foodfix.management.repository.PlateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlateService {

    private final PlateRepository plateRepository;

    public PlateService(PlateRepository plateRepository) {
        this.plateRepository = plateRepository;
    }

    public List<Plate> findAll() {
        return plateRepository.findAll();
    }

    public List<Plate> findFavorites() {
        return plateRepository.findAllByFavoriteIsTrue();
    }

    public List<Plate> findByNameContains(String name) {
        return plateRepository.findByNameContains(name.toLowerCase());
    }

    public Plate save(Plate plate) {
        return plateRepository.save(plate);
    }

    public void deleteById(int id) {
        plateRepository.deleteById(id);
    }

}
