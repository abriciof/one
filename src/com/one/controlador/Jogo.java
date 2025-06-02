package com.one.controlador;

import com.one.modelo.baralho.Monte;
import com.one.modelo.cartas.Carta;
import com.one.modelo.cartas.CartaReverse;
import com.one.modelo.jogadores.*;

public class Jogo {
    private final Monte compra = Monte.gerarBaralhoPadrao();
    private final Monte descarte = new Monte();
    private final ListaJogadores lista = new ListaJogadores();

    public Jogo(String... nomesJogadores) {
        for (String nome : nomesJogadores) lista.adicionar(new Jogador(nome));
    }

    private void distribuirCartasIniciais() {
        for (int i = 0; i < 7; i++) {
            for (Jogador j : lista.getJogadores()) {
                j.getMao().adicionar(compra.comprar());
            }
        }
    }

    public void iniciar() {
        System.out.println("\tONE!\n");

        distribuirCartasIniciais();
        Carta primeira = compra.comprar();
        while (primeira instanceof CartaReverse) {
            compra.empilhar(primeira); compra.embaralhar();
            primeira = compra.comprar();
        }
        System.out.println("Carta Inicial: " + primeira.colorir() + "\n");
        descarte.empilhar(primeira);

        while (true) {
            Jogador j = lista.atual();
            int resultado = j.jogarTurno(compra, descarte);

            if (j.getMao().tamanho() == 0) {
                System.out.println("\n>>> " + j.getNome() + " VENCEU! <<<");
                break;
            }
            if (resultado == 1) lista.inverterDirecao();
            lista.avancar();
        }
    }
}
