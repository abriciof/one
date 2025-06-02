package com.one.modelo.cartas;

public enum Cor {
    VERMELHO, AMARELO, VERDE, AZUL;

    public static final String RESET = "\u001B[0m";

    public String getCodigoAnsi(boolean comReset) {
        String codigo = switch (this) {
            case VERMELHO -> "\u001B[31m";
            case AMARELO -> "\u001B[33m";
            case VERDE -> "\u001B[32m";
            case AZUL -> "\u001B[34m";
        };
        return comReset ? codigo + RESET : codigo;
    }

    public String getCodigoAnsi() {
        return getCodigoAnsi(false);
    }

    public String colorir(String texto) {
        return getCodigoAnsi(false) + texto + RESET;
    }

    public void imprimir(String texto) {
        System.out.print(colorir(texto));
    }

}
