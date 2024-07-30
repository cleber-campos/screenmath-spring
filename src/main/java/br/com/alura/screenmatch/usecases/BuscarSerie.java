package br.com.alura.screenmatch.usecases;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.service.ApiConsumer;
import br.com.alura.screenmatch.service.ConverterDadosJson;
import br.com.alura.screenmatch.service.URLGenerator;

import java.util.Objects;
import java.util.Scanner;

public class BuscarSerie {

    public static Serie getData() {
        final Scanner leitura = new Scanner(System.in);
        final ApiConsumer apiConsumer = new ApiConsumer();
        final ConverterDadosJson conversorJson = new ConverterDadosJson();
        DadosSerie dadosSerie = null;

        System.out.print("Digite o nome da serie: ");
        var nomeSerie = leitura.nextLine();

        //Consumir API
        var json = apiConsumer.getData(URLGenerator.Serie(nomeSerie));
        System.out.println("Buscando dados....");
        if (!json.isEmpty()) {
            System.out.println("Serie encontrada!");
        }

        // Obter dados de serie
        try {
            dadosSerie = conversorJson.getClass(json, DadosSerie.class);
        }catch (NullPointerException e) {
            System.out.println("Dados nao encontrados!");
        }
        return new Serie(Objects.requireNonNull(dadosSerie));
    }
}
