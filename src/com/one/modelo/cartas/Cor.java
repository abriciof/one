package com.one.modelo.cartas;

public enum Cor {
    VERMELHO, AMARELO, VERDE, AZUL;

    public static final String RESET = "\u001B[0m";

    public String getCodigoAnsi(boolean comReset) {
        String codigo;
        switch (this) {
            case VERMELHO:
                codigo = "\u001B[31m";
                break;
            case AMARELO:
                codigo = "\u001B[33m";
                break;
            case VERDE:
                codigo = "\u001B[32m";
                break;
            case AZUL:
                codigo = "\u001B[34m";
                break;
            default:
                codigo = "";
                break;
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
