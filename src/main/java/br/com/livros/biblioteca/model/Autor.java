package br.com.livros.biblioteca.model;

import br.com.livros.biblioteca.dto.AutorDto;
import br.com.livros.biblioteca.main.UI;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;
    private Integer anoNascimento;
    private Integer anoMorte;

    public Autor(){}

    public Autor(AutorDto dto){
        this.nome = dto.nome();
        this.anoNascimento = dto.anoNascimento();
        this.anoMorte = dto.anoMorte();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoMorte() {
        return anoMorte;
    }

    public void setAnoMorte(Integer anoMorte) {
        this.anoMorte = anoMorte;
    }

    @Override
    public String toString() {
        List<String> st = Arrays.asList(
                "AUTOR",
                "",
                nome,
                "Ano de Nascimento: " + anoNascimento,
                "Ano de Falecimento: " + anoMorte
        );

        return UI.mensagemEmCaixa(st, 3);
    }
}