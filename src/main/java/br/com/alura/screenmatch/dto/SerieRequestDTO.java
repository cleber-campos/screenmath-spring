package br.com.alura.screenmatch.dto;

import br.com.alura.screenmatch.model.enums.Categoria;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SerieRequestDTO(Long id,
                              @JsonAlias("Title") String tituloSerie,
                              @JsonAlias("totalSeasons") Integer totalTemporadas,
                              @JsonAlias("imdbRating") Double avaliacao,
                              @JsonAlias("Genre") Categoria genero,
                              @JsonAlias("Actors") String atores,
                              @JsonAlias("Plot") String sinopse,
                              @JsonAlias("Poster") String urlPoster) {

}
