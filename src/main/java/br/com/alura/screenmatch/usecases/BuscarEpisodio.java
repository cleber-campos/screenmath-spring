package br.com.alura.screenmatch.usecases;

import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ApiConsumer;
import br.com.alura.screenmatch.service.ConverterDadosJson;
import br.com.alura.screenmatch.service.URLGenerator;

import java.util.ArrayList;
import java.util.List;

public class BuscarEpisodio {
    private final ApiConsumer apiConsumer = new ApiConsumer();
    private final ConverterDadosJson conversorJson = new ConverterDadosJson();
    private final List<DadosTemporada> listaTemporadas = new ArrayList<>();


            System.out.println("Buscando dados....");
            try {
                    //Consumir API episodios
                    var json = apiConsumer.getData(URLGenerator
                    //Criando lista com dados de episodios
                    var dadosTemporada = conversorJson.getClass(json, DadosTemporada.class);
                    listaTemporadas.add(dadosTemporada);
                }
            } catch (NullPointerException e) {
                System.out.println("Dados nao encontrados! ");
            }
    }
}
