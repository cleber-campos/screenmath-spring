package br.com.alura.screenmatch.model.enums;

public enum Categoria {
    ACAO("Action", "acao"),
    ROMANCE("Romance", "romance"),
    COMEDIA("Comedy", "comedia"),
    DRAMA("Drama","drama"),
    CRIME("Crime","crime"),
    AVENTURA("Adventure","aventura");

    private final String categoriaOmdb;
    private final String categoriaPortugues;


    Categoria(String categoriaOmdb, String categoriaPortugues){
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPortugues = replaceCaracteres(categoriaPortugues);
    }

    public static Categoria fromString(String text) throws IllegalArgumentException {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) return categoria;
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    public static Categoria fromPortugues(String text) throws IllegalArgumentException {
        var textoAjustado = replaceCaracteres(text);
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaPortugues.equalsIgnoreCase(textoAjustado)) return categoria;
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    public static String replaceCaracteres(String texto) {
        if (texto == null) return null;
        return texto.replaceAll("[çÇ]", "c")
                .replaceAll("[áàâãÁÀÂÃ]", "a")
                .replaceAll("[éêÉÊ]", "e")
                .replaceAll("[íÍ]", "i")
                .replaceAll("[óôõÓÔÕ]", "o")
                .replaceAll("[úüÚÜ]", "u");
    }


}
