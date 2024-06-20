package br.com.livros.biblioteca.repository;

import br.com.livros.biblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);

    @Query("Select a from Autor a " +
            "where :ano between a.anoNascimento and a.anoMorte")
    List<Autor> buscarAutoresVivos(Integer ano);



}