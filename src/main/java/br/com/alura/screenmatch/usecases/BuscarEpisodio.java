package br.com.alura.screenmatch.usecases;

import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodios;
import br.com.alura.screenmatch.model.Series;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ApiConsumer;
import br.com.alura.screenmatch.service.ConverterDadosJson;
import br.com.alura.screenmatch.service.URLGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BuscarEpisodio {
    private final Scanner leitura = new Scanner(System.in);
    private final ApiConsumer apiConsumer = new ApiConsumer();
    private final ConverterDadosJson conversorJson = new ConverterDadosJson();
    private final List<DadosTemporada> listaTemporadas = new ArrayList<>();

    public void getData(SerieRepository repository, String nomeSerie){
        //Escolhe a serie que deseja buscar
        var dadosSerie = repository.findAll();
        Optional<Series> serie = dadosSerie.stream()
                .filter(s -> s.getTituloSerie().toLowerCase().contains(nomeSerie.toLowerCase()))
                .findFirst();

        if(serie.isPresent()) {
            var serieEncontrada = serie.get();
                try {
                    for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                        //Consumir API episodios
                        var json = apiConsumer.getData(URLGenerator
                                .Episodio(serieEncontrada.getTituloSerie(), i));

                        //Criando lista com dados de episodios
                        var dadosTemporada = conversorJson.getClass(json, DadosTemporada.class);
                        listaTemporadas.add(dadosTemporada);
                    }

                    List<Episodios> episodios = listaTemporadas.stream()
                            .flatMap(d -> d.episodios().stream()
                                    .map(e -> new Episodios(d.numeroTemporada(), e)))
                            .collect(Collectors.toList());

                    serieEncontrada.setEpisodios(episodios);
                    repository.save(serieEncontrada);

                } catch (NullPointerException e) {
                    System.out.println("Dados nao encontrados! ");
                }
        }
    }
}