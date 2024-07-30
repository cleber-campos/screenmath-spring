package br.com.alura.screenmatch.model.traducao;

import java.util.NoSuchElementException;

public enum Linguagens {
    PORTUGUES ("pt"),
    INGLES ("en");

    public String siglaLinguagem;

    Linguagens(String linguagem){
        this.siglaLinguagem = linguagem;
    }

    public static Linguagens ObterLinguagem(String lingaguem){
        for(Linguagens l : Linguagens.values()){
            if(l.siglaLinguagem.equalsIgnoreCase(lingaguem)){
                return l;
            }
        }
        throw new NoSuchElementException("Lingua nao encontrada!");
    }
}
