package com.br.testes.carga.infra.database.mongo.documents;

import com.br.testes.carga.domains.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class ProductDocument {
    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String code;
    private BigDecimal value;
    private String description;
    private CategoriesDocument categories;

    public static ProductDocument of(final String productId, final Product product) {
        return new ProductDocument(
                productId,
                product.getName(),
                product.getCode(),
                product.getValue(),
                product.getDescription(),
                CategoriesDocument.of(productId, product)
        );
    }

    @Override
    public String toString() {
        return "ProductDocument{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
