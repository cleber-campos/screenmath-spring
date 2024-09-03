package br.com.alura.screenmatch.model.usecases;

import br.com.alura.screenmatch.dto.SerieRequestDTO;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.service.ApiConsumer;
import br.com.alura.screenmatch.service.ConverterDadosJson;
import br.com.alura.screenmatch.service.URLGenerator;

import java.util.Objects;

public class BuscarSerieWeb {

    public static Serie getData(String nomeSerie) {
        final ApiConsumer apiConsumer = new ApiConsumer();
        final ConverterDadosJson conversorJson = new ConverterDadosJson();
        SerieRequestDTO serieRequestDTO = null;

        //Consumir API
        var json = apiConsumer.getData(URLGenerator.Serie(nomeSerie));
        System.out.println("Buscando dados....");
        if (!json.isEmpty()) {
            System.out.println("Serie encontrada!");
        }

        // Obter dados de serie
        try {
            serieRequestDTO = conversorJson.getClass(json, SerieRequestDTO.class);
        }catch (NullPointerException e) {
            System.out.println("Dados nao encontrados!");
        }
        return new Serie(Objects.requireNonNull(serieRequestDTO));
    }
}
