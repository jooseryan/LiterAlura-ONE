package br.com.livros.biblioteca.main;

import br.com.livros.biblioteca.model.Autor;
import br.com.livros.biblioteca.model.Livro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;
import java.util.stream.Collectors;

public class UI {
    private Scanner scanner;

    // Fixed the constructor by adding the missing closing parenthesis
    public UI(Scanner scanner) {
        this.scanner = scanner;
    }

    public String pegarOpcao(String mensagem, Integer paddingHorizontal) {
        System.out.print(mensagemEmCaixa(mensagem, paddingHorizontal));
        System.out.print("┌" + "───" + "> ");
        return scanner.nextLine();
    }

    public Integer pegarOpcaoNumerica() {
        try {
            var string = scanner.nextLine();
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String mensagemEmCaixa(String msg, Integer paddingHorizontal) {
        int len = msg.length() + paddingHorizontal + paddingHorizontal;
        String top = "┌" + "─".repeat(len) + "┐\n";
        String middle = "│" + " ".repeat(paddingHorizontal) + msg + " ".repeat(paddingHorizontal) + "│\n";
        String bottom = "└" + "─".repeat(len) + "┘\n";
        return top + middle + bottom;
    }

    public static String mensagemEmCaixa(List<String> mensagens, Integer paddingHorizontal) {
        //pegando tamanho da maior mensagem
        int len = maiorStringDeLista(mensagens);

        //igualando todas strings
        int finalLen = len;
        mensagens = mensagens.stream().map(m -> m += " ".repeat(finalLen - m.length()))
                .collect(Collectors.toList());

        len += paddingHorizontal + paddingHorizontal;

        String top = "┌" + "─".repeat(len) + "┐\n";
        String middle = mensagens.stream().map(m -> "│" + " ".repeat(paddingHorizontal) + m + " ".repeat(paddingHorizontal) + "│\n")
                .reduce("", String::concat);
        String bottom = "└" + "─".repeat(len) + "┘\n";
        return top + middle + bottom;
    }

    public static String mensagemEmCaixaComInput(List<String> mensagens, Integer paddingHorizontal, String mensagemInput) {
        //pegando tamanho da maior mensagem
        int len = maiorStringDeLista(mensagens);

        //igualando todas strings
        int finalLen = len;
        mensagens = mensagens.stream().map(m -> m += " ".repeat(finalLen - m.length()))
                .collect(Collectors.toList());

        len += paddingHorizontal + paddingHorizontal;

        String top = "┌" + "─".repeat(len) + "┐\n";
        String middle = mensagens.stream().map(m -> "│" + " ".repeat(paddingHorizontal) + m + " ".repeat(paddingHorizontal) + "│\n")
                .reduce("", String::concat);
        String bottomMenu = "├" + "─".repeat(len) + "┤\n";
        mensagemInput += " ".repeat(finalLen - mensagemInput.length());
        String middleInput = "│" + " ".repeat(paddingHorizontal) + mensagemInput + " ".repeat(paddingHorizontal) + "│\n";
        String bottom = "└" + "─".repeat(len) + "┘\n";
        String pointer = "┌" + "───" + "> ";
        return top + middle + bottomMenu + middleInput + bottom + pointer;
    }

    public static Integer maiorStringDeLista(List<String> list) {
        return list.stream()
                .max(Comparator.comparing(String::length)).get().length();
    }

    public void limparConsole() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    public static String listarVariosLivros(List<Livro> livros) {
        List<String> livroStr = new ArrayList<>();

        livroStr.add(livros.size() + " Livros encontrados:");

        List<String> finalLivroStr = livroStr;
        livros.forEach(l -> {
            finalLivroStr.add("─");
            finalLivroStr.add(l.getTitulo());

            finalLivroStr.add("");
            finalLivroStr.addAll(l.getAutores().stream().map(Autor::getNome).toList());
            finalLivroStr.add("");

            finalLivroStr.add("Idioma: " + l.getIdioma());
            finalLivroStr.add("Número de downloads: " + l.getDownloads());
            finalLivroStr.add("─");
        });

        Integer size = maiorStringDeLista(livroStr);
        livroStr = livroStr.stream().map(s -> s.replace("─", "─".repeat(size))).toList();

        return mensagemEmCaixa(livroStr, 5);
    }

    public static String listarVariosAutores(List<Autor> autores) {
        List<String> autorStr = new ArrayList<>();

        autorStr.add(autores.size() + " Autores encontrados");

        List<String> finalLivroStr = autorStr;
        autores.forEach(a -> {
            finalLivroStr.add("─");
            finalLivroStr.add(a.getNome());
            finalLivroStr.add("Ano de nascimento: " + a.getAnoNascimento());
            finalLivroStr.add("Ano de falecimento: " + a.getAnoMorte());
            finalLivroStr.add("─");
        });

        Integer size = maiorStringDeLista(autorStr);
        autorStr = autorStr.stream().map(s -> s.replace("─", "─".repeat(size))).toList();

        return mensagemEmCaixa(autorStr, 5);
    }
}
