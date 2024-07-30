package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.model.traducao.Linguagens;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class URLGenerator {

    public static String Tradutor(String texto, Linguagens lingua1, Linguagens lingua2) {
        String textoParaTraducao = URLEncoder.encode(texto, StandardCharsets.UTF_8);

        String agrupamentoLinguagens = URLEncoder.encode(lingua1.siglaLinguagem +
                "|"+lingua2.siglaLinguagem, StandardCharsets.UTF_8);

        return "https://api.mymemory.translated.net/get?q=" +
                textoParaTraducao + "&langpair=" + agrupamentoLinguagens;
    }

    public static String Serie(String nomeSerie) {
        String URLBASE = "https://www.omdbapi.com/?";
        String API_KEY = "apikey=dd0b7265";
        String PARAM_TITULO = "&t=";
        String SERIE = nomeSerie.replace(" ", "%20");
        return URLBASE + API_KEY + PARAM_TITULO + SERIE;
    }

    public static String Episodio(String nomeSerie, int numeroTemporada) {
        String URLBASE = "https://www.omdbapi.com/?";
        String API_KEY = "apikey=dd0b7265";
        String PARAM_TITULO = "&t=";
        String SERIE = nomeSerie.replace(" ", "%20");
        String TEMPORADA = "&season=";
        return URLBASE + API_KEY + PARAM_TITULO + SERIE + TEMPORADA +
                Integer.toString(numeroTemporada);
    }

}
