package br.com.alura.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IConverterDados {
    <T> T  getClass(String json, Class<T> classe) throws JsonProcessingException;
    //<T> List <T> getList(String json, List<T> classe);
}
