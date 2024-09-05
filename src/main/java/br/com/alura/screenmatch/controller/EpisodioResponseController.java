package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.dto.EpisodioResponseDTO;
import br.com.alura.screenmatch.service.EpisodioResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/series")
public class EpisodioResponseController {

    @Autowired
    private EpisodioResponseService episodioServiceResponse;

    @GetMapping(value = "/{id}/temporadas/todas")
    public List<EpisodioResponseDTO> obterTodasAsTemporadas(@PathVariable Long id){
       return episodioServiceResponse.obterTodasTemporadas(id);
    }

    @GetMapping(value = "/{id}/temporadas/{numeroTemporada}")
    public List<EpisodioResponseDTO> obterTemporadasPoNumero(@PathVariable Long id, @PathVariable Integer numeroTemporada){
        return episodioServiceResponse.obterTemporadasPoNumero(id, numeroTemporada);
    }

}
