package br.com.alura.screenmatch.model.traducao;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosTraducao {
    @JsonAlias(value = "responseData")
    public DadosResposta dadosResposta;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DadosResposta {
        @JsonAlias(value = "translatedText")
        public String textoTraduzido;
    }
}