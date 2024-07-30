package br.com.alura.screenmatch.usecases;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ApiConsumer;
import br.com.alura.screenmatch.service.ConverterDadosJson;
import br.com.alura.screenmatch.service.URLGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class BuscarEpisodio {
    private final Scanner leitura = new Scanner(System.in);
    private final ApiConsumer apiConsumer = new ApiConsumer();
    private final ConverterDadosJson conversorJson = new ConverterDadosJson();
    private final List<DadosTemporada> listaTemporadas = new ArrayList<>();

    public List<DadosTemporada> execute(List<DadosSerie> listaDadosSerie){
        //Exibe lista de series buscadas
        System.out.println("Lista de Series Buscadas:");
        listaDadosSerie.forEach(l-> System.out.println(l.tituloSerie()));
        System.out.println('\n');

        //Escolhe a serie que deseja buscar
        System.out.print("Buscar a serie: ");
        var nomeSerie = leitura.nextLine();
        var dadosSerie =  listaDadosSerie.stream()
                .filter(l -> l.tituloSerie().equalsIgnoreCase(nomeSerie))
                .findFirst().orElse(null);
        String nomeSerieBuscada = null;
        if (dadosSerie != null) {
            System.out.println("Serie encontrada");
            nomeSerieBuscada = dadosSerie.tituloSerie();
        }
        System.out.println("Buscando dados....");

        try {
            for (int numeroTemporada = 1; numeroTemporada <=
                    Objects.requireNonNull(dadosSerie).totalTemporadas(); numeroTemporada++) {
                //Consumir API episodios
               var json = apiConsumer.getData(URLGenerator
                       .Episodio(nomeSerieBuscada, numeroTemporada));

                //Criando lista com dados de episodios
                var dadosTemporada = conversorJson.getClass(json, DadosTemporada.class);
                listaTemporadas.add(dadosTemporada);
            }
        } catch (NullPointerException e) {
            System.out.println("Dados nao encontrados! ");
        }
        return listaTemporadas;
    }
}
