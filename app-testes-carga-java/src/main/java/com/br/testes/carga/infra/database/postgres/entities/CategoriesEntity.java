package com.br.testes.carga.infra.database.postgres.entities;

import com.br.testes.carga.domains.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "categories")
public class CategoriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private List<String> values;
    @OneToOne(mappedBy = "categories")
    private ProductEntity product;

    private CategoriesEntity(final List<String> categories) {
        this.values = categories;
    }

    public static CategoriesEntity from(final Product product) {
        return new CategoriesEntity(product.getCategoriesAsListString());
    }
}
