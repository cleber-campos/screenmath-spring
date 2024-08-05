package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias("Title") String tituloEpisodio,
                            @JsonAlias("Episode") Integer numeroEpisodio,
                            @JsonAlias("imdbRating") String avaliacao,
                            @JsonAlias("Released") String dataLancamento)  {
    @Override
    public String toString() {
        return  "Episodio: " + numeroEpisodio +
                " | Titulo: " + tituloEpisodio +
                " | IMDB: " + avaliacao +
                " | Data de Lancamento: " + dataLancamento + "\n" ;
    }
}
