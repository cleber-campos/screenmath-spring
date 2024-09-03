package br.com.alura.screenmatch.model.repository;

import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EpisodioRepository extends JpaRepository<Episodio, Long> {
    //Exemplos de Derived Queries
    @NotNull
    Optional<Episodio> findById(@NotNull Long idEpisodio);

    //exemplo de JPQL
    @Query("SELECT e FROM Serie s " +
            "JOIN s.episodios e")
    List<Episodio> episodioExisteNoBanco(Serie serie);

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

    @Query("SELECT e FROM  Serie s " +
            "JOIN s.episodios e " +
            " WHERE s.idSerie = :id AND e.numeroTemporada = :numeroTemporada")
    List<Episodio> episodiosPorNumeroTemporada(Long id, Long numeroTemporada);
}
