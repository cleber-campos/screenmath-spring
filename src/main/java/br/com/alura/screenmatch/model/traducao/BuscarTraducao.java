package br.com.alura.screenmatch.model.traducao;

import br.com.alura.screenmatch.service.ApiConsumer;
import br.com.alura.screenmatch.service.ConverterDadosJson;
import br.com.alura.screenmatch.service.URLGenerator;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class BuscarTraducao {
    static ApiConsumer apiConsumer = new ApiConsumer();
    static ConverterDadosJson converter = new ConverterDadosJson();

    public static String inglesPortugues(String text){
        //Consumo API MyMemory
        String json = apiConsumer.getData(URLGenerator
                .Tradutor(text, Linguagens.INGLES, Linguagens.PORTUGUES));
        return converteJson(json);
    }

    public static String portuguesIngles(String text){
        //Consumo API MyMemory
        String json = apiConsumer.getData(URLGenerator
                .Tradutor(text, Linguagens.PORTUGUES, Linguagens.INGLES));
        return converteJson(json);
    }

    static String converteJson(String json){
        DadosTraducao traducao = converter.getClass(json, DadosTraducao.class);
        traducao.dadosResposta.textoTraduzido = URLDecoder
                .decode(traducao.dadosResposta.textoTraduzido, StandardCharsets.UTF_8);
        return traducao.dadosResposta.textoTraduzido;
    }
}
