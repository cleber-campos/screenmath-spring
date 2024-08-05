package br.com.alura.screenmatch.usecases;

import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;

import java.util.Comparator;
import java.util.List;

public class ListarSeriesBuscadas {
    private final SerieRepository repository;

    public ListarSeriesBuscadas(SerieRepository repository) {
        this.repository = repository;
    }

    public void execute(){
    var headerSerie = """
                      ------------------------------
                      DADOS DA SERIE:
                      ------------------------------
                      """;
    System.out.print(headerSerie);
    List<Serie> listaSerie  = repository.findAll();
    listaSerie.stream()
        .sorted(Comparator.comparing(Serie::getGenero))
        .forEach(System.out::println);
    }

}
