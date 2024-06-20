package br.com.livros.biblioteca.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RespostasDto(
        @JsonAlias("count") Integer resultados_encontrados,
        @JsonAlias("results") List<LivroDto> livros
) {
}
