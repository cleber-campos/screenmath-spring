package br.com.alura.screenmatch.usecases;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.Series;
import br.com.alura.screenmatch.service.ApiConsumer;
import br.com.alura.screenmatch.service.ConverterDadosJson;
import br.com.alura.screenmatch.service.URLGenerator;

import java.util.Objects;

public class BuscarSerieWeb {

    public static Series getData(String nomeSerie) {


        final ApiConsumer apiConsumer = new ApiConsumer();
        final ConverterDadosJson conversorJson = new ConverterDadosJson();
        DadosSerie dadosSerie = null;

        //Consumir API
        var json = apiConsumer.getData(URLGenerator.Serie(nomeSerie));
        System.out.println("Buscando dados....");
        if (!json.isEmpty()) {
            System.out.println("Series encontrada!");
        }

        // Obter dados de serie
        try {
            dadosSerie = conversorJson.getClass(json, DadosSerie.class);
        }catch (NullPointerException e) {
            System.out.println("Dados nao encontrados!");
        }
        return new Series(Objects.requireNonNull(dadosSerie));
    }
}
