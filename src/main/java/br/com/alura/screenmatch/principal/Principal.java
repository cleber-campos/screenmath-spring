package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.Series;
import br.com.alura.screenmatch.model.enums.Categoria;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.usecases.BuscarEpisodio;
import br.com.alura.screenmatch.usecases.BuscarSerie;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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
              case 1:
                  Series series = BuscarSerie.getData(validaEntradaSerie());    //1. Buscar Serie na Web
                  repository.save(series);
                  System.out.println("Serie salva banco com sucesso!");
                  break;

              case 2:
                  buscarSeriePorTitulo(validaEntradaSerie());                   //2. Buscar Serie por Titulo
              break;

              case 3:
                  buscarSeriePorAtor();                                         //3. Buscar Serie por Ator
               break;

               case 4:
                    buscarSeriePorGenero();                                          //4. Buscar Serie por Genero
               break;

              case 5:
                  buscarSerieTop5();                                            //4. Buscar Serie TOP 5
              break;

              case 6:
                  headerSerie();
                  List<Series> listaSeries = repository.findAll();
                  listaSeries.stream()
                          .sorted(Comparator
                                  .comparing(Series::getGenero))
                          .forEach(System.out::println);                        //4. Listar series buscadas
                  break;

                case 7:
                    new BuscarEpisodio()
                            .getData(repository, validaEntradaSerie());           //5. Buscar episodios da series
                    break;

              case 0:
                  System.out.println("Saindo....");                             //6. Sair
                  leitura.close();
                  System.exit(0);
                  break;
              default:
                  System.out.println("Opcao invalida!!");
            }
        }
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

    private void buscarSerieTop5() {
    var seriesTop5 = repository
            .findTop5ByOrderByAvaliacaoDesc();
    if(!seriesTop5.isEmpty()) {
        headerSerie();
        seriesTop5.forEach(s -> System.out.println("Avaliacao: " + s.getAvaliacao() + " - "+ s.getTituloSerie()));
    } else System.out.println("Serie nao encontrada");
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

    public String validaEntradaSerie() {
        System.out.print("Digite o nome da series: ");
        String nome = leitura.nextLine();
        if (nome == null) System.out.println("dado invalido!");
        return nome;
    }

    public void headerSerie(){
        var headerSerie = """
                  ------------------------------
                       ** DADOS DA SERIE **
                  ------------------------------
                  """;
        System.out.print(headerSerie);
    }
    public void headerMenu(){
        var menu = """
                  ------------------------------
                  ** CONSULTA FILMES E SERIES **
                  ------------------------------
                  1 - Buscar Serie na Web
                  2 - Buscar Serie por Titulo
                  3 - Buscar Serie por Ator
                  4 - Buscar Serie por categoria
                  5 - Buscar Top 5 Series
                  6 - Listar Todas as Series
                  7 - Buscar Epsodios
                  0 - Sair
                  ------------------------------
                  """;
        System.out.print(menu);
    }

}
