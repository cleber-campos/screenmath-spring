package br.com.alura.screenmatch.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodioDTO(@JsonAlias("Title") String tituloEpisodio,
                          @JsonAlias("Episode") Integer numeroEpisodio,
                          @JsonAlias("imdbRating") String avaliacao,
                          @JsonAlias("Released") String dataLancamento)  {

}
