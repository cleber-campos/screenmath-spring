package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie (@JsonAlias("Title") String tituloSerie,
                         @JsonAlias("totalSeasons") Integer totalTemporadas,
                         @JsonAlias("imdbRating") String avaliacao,
	                     @JsonAlias("Genre") String genero,
                         @JsonAlias("Director") String diretor,
                         @JsonAlias("Writer") String escritor,
                         @JsonAlias("Actors") String atores,
                         @JsonAlias("Plot") String sinopse,
                         @JsonAlias("Poster") String urlPoster) {

    @Override
    public String toString() {
        return  "Titulo: " + tituloSerie + "\n" +
                "Sinopse: " + sinopse + "\n" +
                "Avaliacao: " + avaliacao + "\n" +
                "Genero: " + genero + "\n" +
                "Diretor:" + diretor + "\n" +
                "Atores:" + atores + "\n" +
                "Total de Temporadas: " + totalTemporadas + "\n" +
                "Poster: " + urlPoster + "\n";
    }
}
