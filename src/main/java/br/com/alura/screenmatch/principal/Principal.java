package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.Episodios;
import br.com.alura.screenmatch.model.Series;
import br.com.alura.screenmatch.model.enums.Categoria;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.usecases.BuscarEpisodioWeb;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static br.com.alura.screenmatch.usecases.BuscarSerieWeb.getData;

public class Principal {
    private final Scanner leitura = new Scanner(System.in);
    private final SerieRepository repository;

    public Principal(SerieRepository repository) {
        this.repository = repository;
    }

    public void exibeMenu(){
        var opcao = -1;
        while(true) {
            headerMenu();
            opcao = leitura.nextInt();
            leitura.nextLine();
            switch (opcao){
                case 1:                                                                //Buscar Serie na Web
                    buscarNovaSerie();
                break;

                case 2:
                    buscarSeriePorTitulo(validaEntradaNomeSerie());                     //Buscar Serie por Titulo
                break;

                case 3:
                    buscarSeriePorAtor();                                               //Buscar Serie por Ator
                break;

                case 4:                                                                 //Buscar Serie por Genero
                    buscarSeriePorGenero();
                break;

                case 5:                                                                 //Buscar Serie por temporadas
                    buscarSeriePorTemporadas();
                break;

                case 6:                                                                 //Buscar Serie TOP 5
                    buscarSerieTop5();
                break;

                case 7:                                                                 //Listar series buscadas
                    listarSeriesBuscadas();
                break;

                case 8:                                                                 //Buscar episodios da series
                    buscarNovosEpisodios();
                break;

                case 9:                                                                 //Buscar episodio por nome
                    buscarEpisodioPorNome();
                break;

                case 10:
                    buscarEpisodioTop5();                                               //Buscar episodio top 5

                case 11:
                    buscarEpisodioPorDataLancamento();
                break;

                case 0:                                                                 //Sair
                    System.out.println("Saindo....");
                    leitura.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcao invalida!!");
            }
        }
    }

    private void buscarEpisodioPorDataLancamento() {
        var nomeSerie = validaEntradaNomeSerie();
        var serie = serieExisteNoBanco(nomeSerie);
        if(serie.isPresent()) {
            var serieEncontrada = serie.get();
            var episodios = repository.episodioExisteNoBanco(serieEncontrada);
            if(!episodios.isEmpty()) {
                System.out.print("Digite o ano de lancamento:");
                var anoLancamento = leitura.nextInt();
                var episodiosPorData = repository.episodiosPorAnoLancamento(serieEncontrada, anoLancamento);
                headerEpisodio();
                episodiosPorData.forEach(s -> System.out.println(
                        " Episodio: " + s.getNumeroEpisodio() +
                        " | Temporada : " + s.getNumeroTemporada() +
                        " | Titulo: " + s.gettituloEpisodio() +
                        " | Data Lancamento: " + s.getDataLancamento() +
                        " | Avaliacao: " + s.getAvaliacao()));
            } else System.out.println("\n Os episodios ja existem no banco!");
        } else System.out.println("\n Esta serie nao exite no banco!");
    }

    private void buscarNovosEpisodios() {
        var nomeSerie = validaEntradaNomeSerie();
        var serie = serieExisteNoBanco(nomeSerie);
        if(serie.isPresent()) {
            var serieEncontrada = serie.get();
            var episodio = repository.episodioExisteNoBanco(serieEncontrada);
            System.out.println(episodio);
            if(episodio.isEmpty()) {
                Series serieComEpisodios = new BuscarEpisodioWeb().getData(serieEncontrada);
                //Salvar episodios no banco
                repository.save(serieComEpisodios);
                System.out.println("Episodios salvos no banco");
            } else System.out.println("\n Os episodios ja existem no banco!");
        } else System.out.println("\n Esta serie nao exite no banco!");
    }

    private void buscarNovaSerie() {
        var nomeSerie = validaEntradaNomeSerie();
        var serie = serieExisteNoBanco(nomeSerie);
        if(serie.isPresent()) {
            System.out.println("'\n Serie ja exite no banco!");
        } else {
            Series novaSerie = getData(nomeSerie);
            repository.save(novaSerie);
            System.out.println("'\n Serie salva no banco!");
        }
    }

    private void buscarSeriePorTitulo(String nomeSerie) {
        Optional<Series> seriePorTitulo = repository
                .findByTituloSerieContainingIgnoreCase(nomeSerie);
        if(seriePorTitulo.isPresent()){
            headerSerie();
            System.out.println(seriePorTitulo.get());
        }  else System.out.println("Serie nao encontrada");
    }

    private void buscarSeriePorAtor() {
        System.out.print("Qual o nome do ator: ");
        var nomeAtor = leitura.nextLine();

        System.out.print("Qual o valor da avaliacao min: ");
        var avaliacao = leitura.nextDouble();

        if (!nomeAtor.isEmpty()) {
            var seriesPorAtor = repository
                    .findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor, avaliacao);
            headerSerie();
            seriesPorAtor.forEach(s -> System.out.println(s.getTituloSerie() + ", avaliacao: " + s.getAvaliacao()));
        } else System.out.println("Serie nao encontrada");
    }

    private void buscarSeriePorGenero() {
        System.out.print("Digite o genero da serie: ");
        var nomeGenero = leitura.nextLine();
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        var seriesPorGenero = repository.findByGenero(categoria);
        if(!seriesPorGenero.isEmpty()){
            seriesPorGenero.forEach(System.out::println);
        }  else System.out.println("Serie nao encontrada");
    }

    private void buscarSeriePorTemporadas() {
        System.out.print("Digite o numero de temporadas: ");
        var numeroTemporadas = leitura.nextInt();

        System.out.print("Digite o valor da avaliacao min: ");
        var valorAvaliacao = leitura.nextDouble();

        if (numeroTemporadas != 0) {
           var seriesPorTemporadas = repository.seriesPorTemporadaEAvaliacao(numeroTemporadas, valorAvaliacao);
            headerSerie();
            seriesPorTemporadas.forEach(s -> System.out.println(
                    s.getTituloSerie() + " | Avaliacao: " + s.getAvaliacao()
            +" | Total Temporadas: " + s.getTotalTemporadas()));
        } else System.out.println("Serie nao encontrada");
    }

    private void buscarSerieTop5() {
        var seriesTop5 = repository
                .findTop5ByOrderByAvaliacaoDesc();
        if(!seriesTop5.isEmpty()) {
            headerSerie();
            seriesTop5.forEach(s -> System.out.println("Avaliacao: " + s.getAvaliacao() + " - "+ s.getTituloSerie()));
        } else System.out.println("Serie nao encontrada");
    }

    private void listarSeriesBuscadas() {
        headerSerie();
        List<Series> listaSeries = repository.findAll();
        listaSeries.stream()
                .sorted(Comparator.comparing(Series::getGenero))
                .forEach(System.out::println);
    }

    private void buscarEpisodioPorNome() {
        System.out.print("Digite o nome do episodio: ");
        var nomeEpisodio = leitura.nextLine();
        List<Episodios> episodiosEncontrados = repository.episodiosPorNome(nomeEpisodio);
        headerEpisodio();
        episodiosEncontrados.forEach(s -> System.out.println(
                "Episodio: " + s.getNumeroEpisodio() +
                " | Titulo: "+ s.gettituloEpisodio() +
                " | Avaliacao: " + s.getAvaliacao()));
    }

    private void buscarEpisodioTop5() {
        Optional<Series> seriePorTitulo = repository
                .findByTituloSerieContainingIgnoreCase(validaEntradaNomeSerie());
        if(seriePorTitulo.isPresent()){
            Series serieEncontrada = seriePorTitulo.get();
            List <Episodios> episodiosTop5 = repository.episodiosTop5(
                    serieEncontrada);
            headerEpisodio();
            episodiosTop5.forEach(s -> System.out.println(
                " Episodio: " + s.getNumeroEpisodio() +
                " | Temporada : " + s.getNumeroTemporada() +
                " | Titulo: " + s.gettituloEpisodio() +
                " | Avaliacao: " + s.getAvaliacao()));
        }  else System.out.println("Serie nao encontrada");
    }

    private Optional<Series> serieExisteNoBanco(String nomeSerie) {
        return repository.findByTituloSerieContainingIgnoreCase(nomeSerie);
    }

    private void headerMenu(){
        var menu = """
                  ------------------------------
                  ** CONSULTA FILMES E SERIES **
                  ------------------------------
                  1 - Buscar Serie na Web
                  2 - Buscar Serie por Titulo
                  3 - Buscar Serie por Ator
                  4 - Buscar Serie por categoria
                  5 - Buscar Serie por Total Temporadas / Avaliacao
                  6 - Buscar Top 5 Series
                  7 - Listar Todas as Series
                  8 - Buscar Epsodios
                  9 - Buscar Episodio por Nome
                  10- Buscar Top 5 episodios
                  11- Buscar Epsodios por Data Lancamento
                  0 - Sair
                  ------------------------------
                  """;
        System.out.print('\n' +  menu);
    }

    private void headerSerie(){
        var headerSerie = """
                  ------------------------------
                       ** DADOS DA SERIE **
                  ------------------------------
                  """;
        System.out.print('\n' + headerSerie);
    }

    private void headerEpisodio(){
        var headerEpisodio = """
                  ------------------------------
                      ** DADOS DO EPISODIO **
                  ------------------------------
                  """;
        System.out.print('\n' + headerEpisodio);
    }

    public String validaEntradaNomeSerie() {
        System.out.print("Digite o nome da serie: ");
        String nome = leitura.nextLine();
        if (nome == null) System.out.println("dado invalido!");
        return nome;
    }

}
