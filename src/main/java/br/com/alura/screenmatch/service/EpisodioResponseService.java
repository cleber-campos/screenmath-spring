package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.EpisodioResponseDTO;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.model.repository.EpisodioRepository;
import br.com.alura.screenmatch.model.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EpisodioResponseService {

    @Autowired
    private EpisodioRepository episodioRepository;

    @Autowired
    private SerieRepository serieRepository;

    public List<EpisodioResponseDTO> obterTodasTemporadas(Long id) {
        Optional<Serie> serie = serieRepository.findById(id);
        if(serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodios().stream()
                    .map(e -> new EpisodioResponseDTO(e.getNumeroTemporada(),
                            e.getNumeroEpisodio(), e.gettituloEpisodio()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodioResponseDTO> obterTemporadasPoNumero(Long id, Long numeroTemporada) {
        return converteDadosDTO(episodioRepository.episodiosPorNumeroTemporada(id, numeroTemporada));
    }

    private List<EpisodioResponseDTO> converteDadosDTO (List<Episodio> episodios){
        return episodios.stream()
                .map(e -> new EpisodioResponseDTO(e.getNumeroTemporada(),
                        e.getNumeroEpisodio(), e.gettituloEpisodio()))
                .collect(Collectors.toList());
    }

}
