package br.com.livros.biblioteca.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AutorDto(
        @JsonAlias("name") String nome,
        @JsonAlias("birth_year") Integer anoNascimento,
        @JsonAlias("death_year") Integer anoMorte
) {
}
