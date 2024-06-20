package br.com.livros.biblioteca.service;

import br.com.livros.biblioteca.exceptions.JsonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConversorJson implements IConversorJson{
    ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T converterJsonParaClasse(String json, Class<T> classe) {
        try{
            return mapper.readValue(json, classe);
        }
        catch (JsonProcessingException e){
            throw new JsonException(e.getMessage());
        }
    }

    @Override
    public <T> List<T> converterListaJsonParaLista(String json, Class<T> classe) {
        CollectionType lista = mapper.getTypeFactory().constructCollectionType(List.class, classe);
        try{
            return mapper.readValue(json, lista);
        }
        catch (JsonProcessingException e){
            throw new JsonException(e.getMessage());
        }
    }
}