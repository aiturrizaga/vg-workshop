package com.foodfix.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "sale_detail")
public class SaleDetail {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "plate_id", referencedColumnName = "id", nullable = false)
    private Plate plate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sale_id", referencedColumnName = "id", nullable = false)
    private Sale sale;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "observation", length = 200)
    private String observation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SaleDetail that = (SaleDetail) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
