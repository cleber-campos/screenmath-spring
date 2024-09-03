package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.dto.SerieResponseDTO;
import br.com.alura.screenmatch.service.SerieResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/series")
public class SerieResponseController {

    @Autowired
    private SerieResponseService serieServiceResponse;

    @GetMapping
    public List<SerieResponseDTO> obterTodasAsSeries(){
        return serieServiceResponse.obterTodasAsSeries();
        }

    @GetMapping(value = "/{id}")
    public SerieResponseDTO obterSeriePorId(@PathVariable Long id){
        return serieServiceResponse.obterSeriePorId(id);
    }

    @GetMapping(value = "/top5")
    public List<SerieResponseDTO> obterSeriesTop5(){
        return serieServiceResponse.obterSeriesTop5();
    }

    @GetMapping(value = "/lancamentos")
    public List<SerieResponseDTO> obterSeriesLancamentos(){
        return serieServiceResponse.obterSeriesLancamentos();
    }

    @GetMapping(value = "/categoria/{nomeCategoria}")
    public List<SerieResponseDTO> obterSeriesPorCategoria(@PathVariable String nomeCategoria){
        return serieServiceResponse.obterSeriesPorCategoria(nomeCategoria);
    }

}
