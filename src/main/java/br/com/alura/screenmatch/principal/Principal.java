package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.usecases.BuscarEpisodio;
import br.com.alura.screenmatch.usecases.BuscarSerie;
import br.com.alura.screenmatch.usecases.ListarSeriesBuscadas;
import java.util.ArrayList;
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
                  1 - Buscar Serie
                  2 - Listar Series Buscadas
                  3 - Buscar Epsodios
                  0 - Sair
                  ------------------------------
                  """;
          System.out.print(menu);
          opcao = leitura.nextInt();
          switch (opcao){
              case 1:
                  Serie serie = BuscarSerie.getData();                   //1. Buscar Serie na Web
                  repository.save(serie);                                //2. Salva serie no banco
                  System.out.println("Serie salva no banco");
                  break;
              case 2:
                  new ListarSeriesBuscadas(repository).execute();        //3. Buscar episodios da serie
                  break;
              case 3:
                  var listaTemporada = new BuscarEpisodio()
                          .execute(listaSeries);                        //4. Buscar episodios da serie
                  System.out.println(listaTemporada);
                  break;
              case 0:
                  System.out.println("Saindo....");                     //5. Sair
                  break;
              default:
                  System.out.println("Opcao invalida!!");
            }
        }
    }
}
