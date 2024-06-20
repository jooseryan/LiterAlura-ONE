package br.com.livros.biblioteca.main;

import br.com.livros.biblioteca.dto.LivroDto;
import br.com.livros.biblioteca.dto.RespostasDto;
import br.com.livros.biblioteca.model.Autor;
import br.com.livros.biblioteca.model.Livro;
import br.com.livros.biblioteca.service.AutorService;
import br.com.livros.biblioteca.service.ConversorJson;
import br.com.livros.biblioteca.service.LivroService;
import br.com.livros.biblioteca.service.Requisitor;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final Requisitor requisitor = new Requisitor();
    private final ConversorJson conversor = new ConversorJson();
    private final UI ui = new UI(scanner);
    private final LivroService service;
    private final AutorService autorService;

    private static final String URL_BASE = "https://gutendex.com/books?search=";

    public Main(LivroService service, AutorService autorService) {
        this.service = service;
        this.autorService = autorService;
    }

    public void menu(){

        int option = 33;

        while (option != 0){
            exibirMenu();
            option = ui.pegarOpcaoNumerica();
            ui.limparConsole();

            switch (option){
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEmDeterminadoAno();
                    break;
                case 5:
                    listarLivrosDeDeterminadoIdioma();
                    break;

                case 0:
                    System.out.println(ui.mensagemEmCaixa("Até a próxima!", 6));
                    break;

                default:
                    System.out.println(ui.mensagemEmCaixa("Opção inválida... Digite uma opção válida!", 4));
                    break;
            }
        }

    }

    private void exibirMenu() {
        String menu = UI.mensagemEmCaixaComInput(Arrays.asList(
                "[1] - Buscar um livro pelo título",
                "[2] - Listar livros registrados",
                "[3] - Listar autores registrados",
                "[4] - Listar autores vivos em um determinado ano",
                "[5] - Listar livros em um determinado idioma",
                "",
                "[0] - sair"
        ), 3, "Escolha uma opção:");
        System.out.print(menu);
    }

    private void exibirMenuIdiomas(){
        String menu = UI.mensagemEmCaixa(Arrays.asList(
                "[1] - Inglês(en)",
                "[2] - Espanhol(es)",
                "[3] - Francês(fr)",
                "[4] - Português(pt)"
        ),3);
        System.out.println(menu);
    }

    private void listarLivrosRegistrados() {
        List<Livro> livrosEncontrados = service.recuperarTodosLivros();

        if(livrosEncontrados.isEmpty())
            System.out.println(ui.mensagemEmCaixa("Nenhum resultado encontrado no registros de livros!", 6));
        else {
            System.out.println(UI.listarVariosLivros(livrosEncontrados));
        }
    }

    private void listarLivrosDeDeterminadoIdioma() {
        exibirMenuIdiomas();
        String opcao = ui.pegarOpcao("Por favor, digite o idioma (Somente sigla do idioma): ", 3);

        List<Livro> livrosEncontrados = service.recuperarLivrosDeIdioma(opcao);
        System.out.println(UI.listarVariosLivros(livrosEncontrados));

    }

    private void listarAutoresVivosEmDeterminadoAno() {
        System.out.print(ui.mensagemEmCaixa("Digite um ano:", 6));
        System.out.print("┌" + "───" + "> ");
        Integer ano = ui.pegarOpcaoNumerica();
        List<Autor> autoresVivos = autorService.recuperarTodosAutoresVivos(ano);

        System.out.println(UI.listarVariosAutores(autoresVivos));

    }

    private void buscarLivroPorTitulo(){
        String nomeLivro = ui.pegarOpcao("Digite o título do livro:", 6)
                .trim().replace(" ", "%20");
        String url = URL_BASE + nomeLivro;
        System.out.println("\ncarregando...");

        HttpResponse<String> response = requisitor.requisitar(url);

        ui.limparConsole();

        RespostasDto resultados = conversor.converterJsonParaClasse(response.body(), RespostasDto.class);

        if(resultados.resultados_encontrados() > 0){
            List<LivroDto> livrosList = resultados.livros();

            Livro livro = new Livro(livrosList.get(0));
            service.salvarLivro(livro);

            System.out.println(livro);
        }
        else {
            System.out.println(ui.mensagemEmCaixa("Nenhum resultado encontrado com o título informado", 6));
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autoresEncontrados = autorService.recuperarTodosAutores();
        if(autoresEncontrados.isEmpty())
            System.out.println(ui.mensagemEmCaixa("Não foram encontrados registros de autores!", 6));
        else {
            System.out.println(UI.listarVariosAutores(autoresEncontrados));
        }
    }
}
