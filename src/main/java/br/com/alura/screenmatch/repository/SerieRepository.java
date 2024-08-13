package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Episodios;
import br.com.alura.screenmatch.model.Series;
import br.com.alura.screenmatch.model.enums.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Series, Long> {
    //Exemplos de Derived Queries
    Optional<Series> findByTituloSerieContainingIgnoreCase(String nomeSerie);

    @Query("SELECT e FROM Series s " +
            "JOIN s.episodios e")
    List<Episodios> episodioExisteNoBanco(Series serie);

    List<Series> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, Double avaliacao);

    List<Series> findTop5ByOrderByAvaliacaoDesc();

    List<Series> findByGenero(Categoria categoria);

    //exemplo de query native
    //@Query(value = "SELECT * FROM tb_series " +
    //                "WHERE tb_series.total_temporadas <=5 "
    //                "AND tb_series.avaliacao >=8", nativeQuery = true)
    //List<Series> seriesPorTemporadaEAvaliacao();

    //exemplo de JPQL
    @Query("SELECT s FROM Series s " +
            "WHERE s.totalTemporadas <= :totalTemporadas " +
            "AND s.avaliacao >= :avaliacao")
    List<Series> seriesPorTemporadaEAvaliacao(int totalTemporadas, double avaliacao);

    @Query("SELECT e FROM  Series s " +
            "JOIN s.episodios e " +
            " WHERE LOWER(e.tituloEpisodio) LIKE LOWER(CONCAT('%', :nomeEpisodio, '%'))")
    List<Episodios> episodiosPorNome(String nomeEpisodio);

    @Query("SELECT e FROM Series s " +
            "JOIN s.episodios e " +
            " WHERE s = :serie ORDER BY e.avaliacao DESC LIMIT 5 ")
    List<Episodios> episodiosTop5(Series serie);

    @Query("SELECT e FROM  Series s " +
            "JOIN s.episodios e " +
            " WHERE s = :series AND YEAR(e.dataLancamento) = :anoLancamento")
    List<Episodios> episodiosPorAnoLancamento(Series series, int anoLancamento);
}
