package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.model.traducao.BuscarTraducao;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_episodios")
public class Episodios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEpisodio;
    private String  tituloEpisodio;
    private Double avaliacao;
    private Integer numeroEpisodio;
    private Integer numeroTemporada;
    private LocalDate dataLancamento;

    @ManyToOne
    @JoinColumn(name = "serie_id")
    private Series serie;

    public Episodios(){
    }
    public Episodios(Integer numeroTemporada, DadosEpisodio episodioDTO) {
        this.numeroTemporada = numeroTemporada;
        this.tituloEpisodio = BuscarTraducao.inglesPortugues(episodioDTO.tituloEpisodio()).trim();
        this.numeroEpisodio = episodioDTO.numeroEpisodio();
        try {
            this.avaliacao = Double.valueOf(episodioDTO.avaliacao());
        } catch (NumberFormatException e){
            this.avaliacao = 0.00;
        }
        try {
            this.dataLancamento = LocalDate.parse(episodioDTO.dataLancamento());
        } catch (Exception e){
            this.dataLancamento = null;
        }

    }

    public Long getIdEpisodio() {
        return idEpisodio;
    }

    public void setIdEpisodio(Long idEpisodio) {
        this.idEpisodio = idEpisodio;
    }

    public Integer getNumeroTemporada() {
        return numeroTemporada;
    }

    public void setNumeroTemporada(Integer numeroTemporada) {
        this.numeroTemporada = numeroTemporada;
    }

    public String gettituloEpisodio() {
        return tituloEpisodio;
    }

    public void settituloEpisodio(String tituloEpisodio) {
        this.tituloEpisodio = tituloEpisodio;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Series getSerie() {
        return serie;
    }

    public void setSerie(Series serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        return  "titulo Episodio: " + tituloEpisodio +
                ", Numero Episodio: " + numeroEpisodio +
                ", Avaliacao: " + avaliacao +
                ", Numero de Temporadas: " + numeroTemporada +
                ", Data de Lancamento: " + dataLancamento;
    }
}
