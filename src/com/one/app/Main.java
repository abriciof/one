package com.one.app;

import com.one.controlador.Jogo;

public class Main {
    public static void main(String[] args) {
        Jogo jogo = new Jogo("Fernanda", "Lucas", "Rômulo", "Jeovana", "Fabrício");
        jogo.iniciar();
    }
}
