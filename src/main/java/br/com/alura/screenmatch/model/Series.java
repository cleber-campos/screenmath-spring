package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.model.enums.Categoria;
import br.com.alura.screenmatch.model.traducao.BuscarTraducao;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;


@Entity
@Table(name = "tb_series")
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSerie;
    @Column(unique = true)
    private String tituloSerie;
    private Integer totalTemporadas;
    private Double avaliacao;
    @Enumerated(EnumType.STRING)
    private Categoria genero;
    private String diretor;
    private String escritor;
    private String atores;
    private String sinopse;
    private String urlPoster;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episodios> episodios = new ArrayList<>();

    public Series() {
    }

    public Series(DadosSerie dadosSerie){
        this.tituloSerie = dadosSerie.tituloSerie();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.avaliacao = OptionalDouble.of(Double.parseDouble(dadosSerie.avaliacao())).orElse(0);
        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim());
        this.diretor = dadosSerie.diretor();
        this.escritor = dadosSerie.escritor();
        this.atores = dadosSerie.atores();
        this.sinopse = BuscarTraducao.inglesPortugues(dadosSerie.sinopse()).trim();
        this.urlPoster = dadosSerie.urlPoster();
    }

    public Long getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Long idSerie) {
        this.idSerie = idSerie;
    }

    public List<Episodios> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodios> episodios) {
        episodios.forEach(e -> e.setSerie(this));
        this.episodios = episodios;
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

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getEscritor() {
        return escritor;
    }

    public void setEscritor(String escritor) {
        this.escritor = escritor;
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

    @Override
    public String toString() {
        return "Genero: " + genero +
                " Titulo: " + tituloSerie +
                ", Total de Temporadas: " + totalTemporadas +
                ", Avaliacao IMDB: " + avaliacao +
                ", Diretor: " + diretor +
                ", Atores: " + atores +
                ", Sinopse: " + sinopse +
                ", urlPoster: " + urlPoster;
    }
}
