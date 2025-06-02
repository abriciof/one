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
        Carta topo = descarte.topo();
        Carta jogada = mao.jogarCartaValida(topo);

        if (jogada == null) {                 // comprar
            if (compra.tamanho() == 0) {      // repor monte de compra
                Carta salva = descarte.comprar();
                descarte.moverPara(compra);
                compra.embaralhar();
                descarte.empilhar(salva);
            }
            mao.adicionar(compra.comprar());
            System.out.println(nome + " comprou.");
            return 2;
        }
        descarte.empilhar(jogada);
        System.out.println(nome + " jogou " + jogada);
        return (jogada instanceof CartaReverse) ? 1 : 0;
    }
}
