package com.one.modelo.jogadores;

import com.one.modelo.baralho.*;
import com.one.modelo.cartas.*;

public class Jogador {
    private final String nome;
    private final Mao mao = new Mao();
    private boolean declarouUno = false;

    public Jogador(String nome) { this.nome = nome; }

    public String getNome() { return nome; }

    public Mao getMao() { return mao; }

    public void declararUno() { declarouUno = true; }
    public boolean deveSerPenalizadoPorNaoDeclararUno() {
        return mao.tamanho() == 1 && !declarouUno;
    }
    public void resetarDeclaracaoUno() { declarouUno = false; }

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
            Carta comprada = compra.comprar();
            mao.adicionar(comprada);
            System.out.println("\t" + nome + " comprou " + comprada.colorir());

            Carta novaJogada = mao.jogarCartaValida(topo);
            if (novaJogada == null) {
                if (compra.tamanho() == 0) {
                    Carta salva = descarte.comprar();
                    descarte.moverPara(compra);
                    compra.embaralhar();
                    descarte.empilhar(salva);
                }
                return 2;
            }
    
            descarte.empilhar(novaJogada);
            System.out.println(nome + " jogou " + novaJogada.colorir());

            return (novaJogada instanceof CartaReverse) ? 1 : 0;
        }
        descarte.empilhar(jogada);
        System.out.println(nome + " jogou " + jogada.colorir());

        return (jogada instanceof CartaReverse) ? 1 : 0;
    }
}
