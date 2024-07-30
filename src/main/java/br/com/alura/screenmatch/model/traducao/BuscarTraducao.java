package br.com.alura.screenmatch.model.traducao;

import br.com.alura.screenmatch.service.ApiConsumer;
import br.com.alura.screenmatch.service.ConverterDadosJson;
import br.com.alura.screenmatch.service.URLGenerator;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class BuscarTraducao {

    public static String inglesPortugues(String text){
        ApiConsumer apiConsumer = new ApiConsumer();
        ConverterDadosJson converter = new ConverterDadosJson();

        //Consumo API MyMemory
        String json = apiConsumer.getData(URLGenerator
                .Tradutor(text, Linguagens.INGLES, Linguagens.PORTUGUES));

        //Conversao Json
        DadosTraducao traducao = converter.getClass(json, DadosTraducao.class);
        traducao.dadosResposta.textoTraduzido = URLDecoder
                .decode(traducao.dadosResposta.textoTraduzido, StandardCharsets.UTF_8);
        return traducao.dadosResposta.textoTraduzido;
    }

}
