package br.com.livros.biblioteca.repository;

import br.com.livros.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l from Livro l " +
            "WHERE l.idioma = :idioma")
    List<Livro> recuperarLivrosPorIdioma(String idioma);
}