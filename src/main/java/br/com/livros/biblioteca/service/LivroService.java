package br.com.livros.biblioteca.service;

import br.com.livros.biblioteca.model.Autor;
import br.com.livros.biblioteca.model.Livro;
import br.com.livros.biblioteca.repository.AutorRepository;
import br.com.livros.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private AutorRepository autorRepository;

    public void salvarLivro(Livro livro){

        for(int i = 0; i < livro.getAutores().size(); i++){
            Autor a = livro.getAutores().get(i);
            Optional<Autor> autor = autorRepository.findByNome(a.getNome());
            //se existir
            if(autor.isPresent()){
                livro.getAutores().set(i, autor.get());
            }
            //se não existir autor
            else {
                autorRepository.save(a);
            }
        }

        repository.save(livro);
    }

    public List<Livro> recuperarTodosLivros(){
        return repository.findAll();
    }

    public List<Livro> recuperarLivrosDeIdioma(String idioma) {
        return repository.recuperarLivrosPorIdioma(idioma);
    }
}