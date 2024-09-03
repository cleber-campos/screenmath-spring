package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.SerieResponseDTO;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.model.enums.Categoria;
import br.com.alura.screenmatch.model.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieResponseService {

    @Autowired
    private SerieRepository repository;
    
    public List<SerieResponseDTO> obterTodasAsSeries(){
        return converteDadosSerieDTO(repository.findAll());
    }
    
    public SerieResponseDTO obterSeriePorId(Long id){
        var serie = repository.findById(id);
        if(serie.isPresent()){
            var s = serie.get();
            return new SerieResponseDTO(s.getIdSerie(), s.getTituloSerie(), s.getTotalTemporadas(),
                    s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getSinopse(),s.getUrlPoster());
        }
    return  null;
    }

    public List<SerieResponseDTO> obterSeriesLancamentos(){
        return converteDadosSerieDTO(repository.lancamentosMaisRescentes());
    }

    public List<SerieResponseDTO> obterSeriesTop5(){
        return converteDadosSerieDTO(repository.obterSeriesLancamentos());
    }

    public List<SerieResponseDTO> obterSeriesPorCategoria(String nomeCategoria) {
        Categoria genero = Categoria.fromPortugues(nomeCategoria);
        return converteDadosSerieDTO(repository.findByGenero(genero));
    }

    private List<SerieResponseDTO> converteDadosSerieDTO (List<Serie> series){
        return series.stream().map(s -> new SerieResponseDTO(s.getIdSerie(), s.getTituloSerie(), s.getTotalTemporadas(),
                        s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getSinopse(), s.getUrlPoster()))
                .collect(Collectors.toList());
    }


}
