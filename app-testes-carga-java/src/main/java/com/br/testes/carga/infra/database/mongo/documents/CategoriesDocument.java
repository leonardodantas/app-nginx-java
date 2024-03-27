package com.br.testes.carga.infra.database.mongo.documents;

import com.br.testes.carga.domains.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "categories")
public class CategoriesDocument {

    @Id
    private String id;
    private String productId;
    private List<String> values;

    public static CategoriesDocument of(final String productId, final Product product) {
        return new CategoriesDocument(UUID.randomUUID().toString(), productId, product.getCategoriesAsListString());
    }
}
