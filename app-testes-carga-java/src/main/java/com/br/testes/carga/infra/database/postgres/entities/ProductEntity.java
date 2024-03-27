package com.br.testes.carga.infra.database.postgres.entities;

import com.br.testes.carga.domains.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

import java.math.BigDecimal;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private BigDecimal value;
    private String description;
    @With
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categories_id", referencedColumnName = "id")
    private CategoriesEntity categories;

    private ProductEntity(final String name, final BigDecimal value, final String description) {
        this.name = name;
        this.value = value;
        this.description = description;
    }

    public static ProductEntity from(final Product product) {
        return new ProductEntity(product.getName(), product.getValue(), product.getDescription());
    }
}
