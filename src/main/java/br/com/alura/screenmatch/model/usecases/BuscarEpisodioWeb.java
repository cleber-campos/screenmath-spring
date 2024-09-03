package br.com.alura.screenmatch.model.usecases;

import br.com.alura.screenmatch.dto.TemporadaDTO;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.service.ApiConsumer;
import br.com.alura.screenmatch.service.ConverterDadosJson;
import br.com.alura.screenmatch.service.URLGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuscarEpisodioWeb {
    private final ApiConsumer apiConsumer = new ApiConsumer();
    private final ConverterDadosJson conversorJson = new ConverterDadosJson();
    private final List<TemporadaDTO> listaTemporadas = new ArrayList<>();
    private Serie serieComEpisodios;

    public BuscarEpisodioWeb() {
    }
    public Serie getData(Serie serie){
        try {
            System.out.println("Buscando episodios....");
            for (int i = 1; i <= serie.getTotalTemporadas(); i++) {
                //Consumir API com episodios da Serie
                var json = apiConsumer.getData(URLGenerator
                        .Episodio(serie.getTituloSerie(), i));

                //Criar lista de temporadas
                var dadosTemporada = conversorJson.getClass(json, TemporadaDTO.class);
                listaTemporadas.add(dadosTemporada);
            }
            //Criar lista de episodios
            List<Episodio> episodios = listaTemporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numeroTemporada(), e)))
                    .collect(Collectors.toList());
            serie.setEpisodios(episodios);
            //Salvar episodios no banco
            //repository.save(serieComEpisodios);
            //System.out.println("Episodio salvos no banco");
            this.serieComEpisodios = serie;
        } catch (NullPointerException e) {
            System.out.println("Dados nao encontrados! ");
        }
        return serie;
    }
    public Serie getEpisodios(){
        return serieComEpisodios;
    }

}
