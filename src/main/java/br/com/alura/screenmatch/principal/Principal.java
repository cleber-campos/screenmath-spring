package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.Series;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.usecases.BuscarEpisodio;
import br.com.alura.screenmatch.usecases.BuscarSerie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private final Scanner leitura = new Scanner(System.in);
    private final List<DadosSerie> listaSeries = new ArrayList<>();
    private final SerieRepository repository;

    public Principal(SerieRepository repository) {
        this.repository = repository;
    }

    public void exibeMenu(){
        var opcao = -1;
        while(opcao != 0) {
            var menu = """
                  
                  ------------------------------
                  ** CONSULTA FILMES E SERIES **
                  ------------------------------
                  1 - Buscar Series
                  2 - Listar Series Buscadas
                  3 - Buscar Epsodios
                  0 - Sair
                  ------------------------------
                  """;
          System.out.print(menu);
          opcao = leitura.nextInt();
          leitura.nextLine();
          switch (opcao){
              case 1:
                  Series series = BuscarSerie.getData(entradaSerie());     //1. Busca Series na Web
                  repository.save(series);                                //2. Salva series no banco
                  System.out.println("Serie salva no banco");
                  break;

              case 2:
                  var headerSerie = """
                      ------------------------------
                      DADOS DA SERIE:
                      ------------------------------
                      """;
                  System.out.print(headerSerie);
                  List<Series> listaSeries = repository.findAll();
                  listaSeries.stream()
                          .sorted(Comparator
                                  .comparing(Series::getGenero))
                          .forEach(System.out::println);                //3. Buscar episodios da series
                  break;

              case 3:
                  new BuscarEpisodio()
                          .getData(repository, entradaSerie());         //4. Buscar episodios da series
                  break;

              case 0:
                  System.out.println("Saindo....");                     //5. Sair

                  break;

              default:
                  System.out.println("Opcao invalida!!");
            }
        }
    }

    public String entradaSerie() {
        System.out.print("Digite o nome da series: ");
        String nome = leitura.nextLine();
        if (nome == null) System.out.println("dado invalido!");
        return nome;
    }
}
