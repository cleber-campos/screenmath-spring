package br.com.alura.screenmatch.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record TemporadaDTO(@JsonAlias("Season") Integer numeroTemporada,
                           @JsonAlias("Episodes") List<EpisodioDTO> episodios) {

    @Override
    public String toString() {
        return  "Temporada: " + numeroTemporada + "\n" +
                episodios + "\n";
    }
}
