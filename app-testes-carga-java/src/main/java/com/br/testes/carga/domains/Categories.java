package com.br.testes.carga.domains;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Categories {
    private String id;
    private List<String> value;

}
