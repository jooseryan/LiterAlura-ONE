package br.com.livros.biblioteca.service;

import java.util.List;

public interface IConversorJson {
    <T> T converterJsonParaClasse(String json, Class<T> classe);
    <T> List<T> converterListaJsonParaLista(String json, Class<T> classe);
}