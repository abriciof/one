package com.one.modelo.jogadores;

import com.one.modelo.baralho.*;
import com.one.modelo.cartas.*;

public class Jogador {
    private final String nome;
    private final Mao mao = new Mao();

    public Jogador(String nome) { this.nome = nome; }

    public String getNome() { return nome; }

    public Mao getMao() { return mao; }

    public int jogarTurno(Monte compra, Monte descarte) {

        reporMonteSeNecessario(compra, descarte);

        Carta topo = descarte.topo();
        Carta jogada = mao.jogarCartaValida(topo);

        if (jogada == null) {
            comprarCarta(compra, descarte);
            jogada = mao.jogarCartaValida(topo);
            if (jogada == null) return 0;
        }

        descarte.empilhar(jogada);
        System.out.println("[DESCARTE] " + nome + " jogou \n" + jogada.desenharComoTexto());

        verificarOne(compra, descarte);

        if (jogada instanceof CartaReverse){ return 1; }
        else if (jogada instanceof CartaBloqueio){ return 2; }
        else { return 0; }
    }

    private void reporMonteSeNecessario(Monte compra, Monte descarte) {
        if (compra.tamanho() == 0) {
            Carta salva = descarte.comprar();
            descarte.moverPara(compra);
            compra.embaralhar();
            descarte.empilhar(salva);
        }
    }

    private void comprarCarta(Monte compra, Monte descarte) {
        reporMonteSeNecessario(compra, descarte);
        Carta c = compra.comprar();
        mao.adicionar(c);
        System.out.println("[COMPRA] " + nome + " comprou \n" + c.desenharComoTexto());
    }

    private void verificarOne(Monte compra, Monte descarte) {
        if (mao.tamanho() == 1) {
            boolean gritou = Math.random() < 0.5;

            if (gritou) {
                System.out.println("[ONE!] " + nome + " gritou ONE!");
            } else {
                System.out.println("[PENALIDADE] " + nome + " esqueceu de gritar ONE! Comprando 2.");
                for (int i = 0; i < 2; i++) comprarCarta(compra, descarte);
            }
        }
    }

}
