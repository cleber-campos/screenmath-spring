package br.com.alura.screenmatch.dto;

import br.com.alura.screenmatch.model.enums.Categoria;

public record SerieResponseDTO(Long id,
                               String titulo,
                               Integer totalTemporadas,
                               Double avaliacao,
                               Categoria genero,
                               String atores,
                               String sinopse,
                               String poster) {
}
