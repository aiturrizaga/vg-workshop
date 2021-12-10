package com.foodfix.management.repository;

import com.foodfix.management.model.Plate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlateRepository extends JpaRepository<Plate, Integer> {

    List<Plate> findAllByFavoriteIsTrue();

    @Query(value = "from Plate p where lower(p.name) like %?1%")
    List<Plate> findByNameContains(String name);
}
