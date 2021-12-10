package com.foodfix.management.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "table_number")
    private int tableNumber;

    @Column(name = "sale_date", nullable = false)
    private LocalDate saleDate = LocalDate.now();

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @OneToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL, mappedBy = "sale")
    @ToString.Exclude
    private List<SaleDetail> details;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Sale sale = (Sale) o;
        return id != null && Objects.equals(id, sale.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
