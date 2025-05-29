package com.one.controlador;

import com.one.modelo.baralho.Monte;
import com.one.modelo.cartas.Carta;
import com.one.modelo.cartas.CartaReverse;
import com.one.modelo.jogadores.*;

public class Jogo {
    private final Monte compra = Monte.gerarBaralhoPadrao();
    private final Monte descarte = new Monte();
    private final FilaJogadores fila = new FilaJogadores();

    public Jogo(String... nomesJogadores) {
        for (String nome : nomesJogadores) fila.adicionar(new Jogador(nome));
        // distribuir 7 cartas a cada jogador
//        for (int k = 0; k < 7; k++)
//            fila.adicionar(null); // *** veremos abaixo
    }

    /** Pré-distribuição que respeita quantidade de jogadores   */
    private void distribuirCartasIniciais() {
        for (int i = 0; i < 7; i++) {
            for (Jogador j : fila.getJogadores()) {
                j.getMao().adicionar(compra.comprar());
            }
        }
    }

    public void iniciar() {
        distribuirCartasIniciais();
        // primeira carta na mesa não pode ser Reverse
        Carta primeira = compra.comprar();
        while (primeira instanceof CartaReverse) {
            compra.empilhar(primeira); compra.embaralhar();
            primeira = compra.comprar();
        }
        descarte.empilhar(primeira);

        /* ---------- loop principal ---------- */
        while (true) {
            Jogador j = fila.atual();
            int resultado = j.jogarTurno(compra, descarte);

            if (j.getMao().tamanho() == 0) {
                System.out.println("\n>>> " + j.getNome() + " VENCEU! <<<");
                break;
            }
            if (resultado == 1) fila.inverterDirecao();
            fila.avancar();
        }
    }
}
