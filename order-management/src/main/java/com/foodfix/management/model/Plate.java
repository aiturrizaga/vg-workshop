package com.foodfix.management.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "plate")
public class Plate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Name is required")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull
    @PositiveOrZero(message = "Price must be 0 or positive")
    @Column(name = "price", nullable = false)
    private BigDecimal price = BigDecimal.ZERO;

    @Column(name = "favorite", nullable = false)
    private boolean favorite = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Plate plate = (Plate) o;
        return id != null && Objects.equals(id, plate.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
