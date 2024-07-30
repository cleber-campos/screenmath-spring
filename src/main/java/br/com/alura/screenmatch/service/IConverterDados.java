package br.com.alura.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IConverterDados {
    <T> T  getClass(String json, Class<T> classe) throws JsonProcessingException;
    //<T> List <T> getList(String json, List<T> classe);
}
