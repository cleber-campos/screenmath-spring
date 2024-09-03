package br.com.alura.screenmatch.model.repository;

import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.model.enums.Categoria;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    //Exemplos de Derived Queries
    @NotNull
    Optional<Serie> findById(@NotNull Long idSerie);

    Optional<Serie> findByTituloSerieContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, Double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);

    //exemplo de JPQL
    @Query("SELECT e FROM Serie s " +
            "JOIN s.episodios e")
    List<Episodio> episodioExisteNoBanco(Serie serie);

    //listar series com episodio da 1 temporada no ano atual
    @Query("SELECT s FROM Serie s " +
            "JOIN s.episodios e " +
            "GROUP BY s " +
            "ORDER BY MAX(e.dataLancamento) DESC LIMIT 5")
    List<Serie> obterSeriesLancamentos();

    @Query("SELECT s FROM Serie s " +
            "WHERE s.totalTemporadas <= :totalTemporadas " +
            "AND s.avaliacao >= :avaliacao")
    List<Serie> seriesPorTemporadaEAvaliacao(int totalTemporadas, double avaliacao);

    @Query("SELECT e FROM  Serie s " +
            "JOIN s.episodios e " +
            " WHERE LOWER(e.tituloEpisodio) LIKE LOWER(CONCAT('%', :nomeEpisodio, '%'))")
    List<Episodio> episodiosPorNome(String nomeEpisodio);

    @Query("SELECT e FROM Serie s " +
            "JOIN s.episodios e " +
            " WHERE s = :serie ORDER BY e.avaliacao DESC LIMIT 5 ")
    List<Episodio> episodiosTop5(Serie serie);

    @Query("SELECT e FROM  Serie s " +
            "JOIN s.episodios e " +
            " WHERE s = :serie AND YEAR(e.dataLancamento) = :anoLancamento")
    List<Episodio> episodiosPorAnoLancamento(Serie serie, int anoLancamento);

    @Query("SELECT s FROM Serie s " +
            "JOIN s.episodios e " +
            "GROUP BY s " +
            "ORDER BY MAX(e.dataLancamento) DESC LIMIT 5")
    List<Serie>lancamentosMaisRescentes();

    @Query("SELECT e FROM  Serie s " +
            "JOIN s.episodios e " +
            " WHERE s.idSerie = :id AND e.numeroTemporada = :numeroTemporada")
    List<Episodio> episodiosPorNumeroTemporada(Long id, Long numeroTemporada);
}
