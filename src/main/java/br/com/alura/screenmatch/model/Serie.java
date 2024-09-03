package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.dto.SerieRequestDTO;
import br.com.alura.screenmatch.model.enums.Categoria;
import br.com.alura.screenmatch.model.traducao.BuscarTraducao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "tb_series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSerie;
    @Column(unique = true)
    private String tituloSerie;
    private Double avaliacao;
    private Integer totalTemporadas;
    private String sinopse;
    @Enumerated(EnumType.STRING)
    private Categoria genero;
    private String atores;
    private String urlPoster;

    @JsonIgnore
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episodio> episodios = new ArrayList<>();

    public Serie() {
    }

    public Serie(SerieRequestDTO serieRequestDTO){
        this.tituloSerie = serieRequestDTO.tituloSerie();
        this.totalTemporadas = serieRequestDTO.totalTemporadas();
        this.avaliacao = OptionalDouble.of(Double.parseDouble(String.valueOf(serieRequestDTO.avaliacao()))).orElse(0);
        this.genero = Categoria.fromString(String.valueOf(serieRequestDTO.genero()).split(",")[0].trim());
        this.atores = serieRequestDTO.atores();
        this.sinopse = BuscarTraducao.inglesPortugues(serieRequestDTO.sinopse()).trim();
        this.urlPoster = serieRequestDTO.urlPoster();
    }

    public Long getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Long idSerie) {
        this.idSerie = idSerie;
    }

    public String getTituloSerie() {
        return tituloSerie;
    }

    public void setTituloSerie(String tituloSerie) {
        this.tituloSerie = tituloSerie;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e -> e.setSerie(this));
        this.episodios = episodios;
    }

    @Override
    public String toString() {
        return  "Titulo: " + tituloSerie +
                ", Avaliacao IMDB: " + avaliacao +
                ", Total de Temporadas: " + totalTemporadas +
                ", Sinopse: " + sinopse +
                ", Genero: " + genero +
                ", Atores: " + atores +
                ", urlPoster: " + urlPoster;
    }
}
