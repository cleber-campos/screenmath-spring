package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Series, Long> {
    Optional<Series> findByTituloSerieContainingIgnoreCase(String nomeSerie);

    List<Series> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, Double avaliacao);

    List<Series> findTop5ByOrderByAvaliacaoDesc();
}
