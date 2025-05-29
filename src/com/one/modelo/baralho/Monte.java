package com.one.modelo.baralho;

import com.one.modelo.cartas.*;
import java.util.*;

/** Pilha (Deque) representando monte de compra ou descarte. */
public class Monte {
    private final Deque<Carta> cartas = new ArrayDeque<>();

    /* ---------- operações básicas ---------- */

    public void empilhar(Carta c) { cartas.push(c); }

    public Carta comprar() { return cartas.pop(); }

    public Carta topo() { return cartas.peek(); }

    public int tamanho() { return cartas.size(); }

    /* ---------- utilidades ---------- */

    public void embaralhar() {
        List<Carta> lista = new ArrayList<>(cartas);
        Collections.shuffle(lista);
        cartas.clear();
        lista.forEach(cartas::push);
    }

    /** Gera baralho UNO simples: 4 cores × (0-9 duas cópias exceto 0) + 2 Reverse por cor. */
    public static Monte gerarBaralhoPadrao() {
        Monte m = new Monte();
        for (Cor cor : Cor.values()) {
            m.empilhar(new CartaNumerica(cor, 0));
            for (int n = 1; n <= 9; n++) {
                m.empilhar(new CartaNumerica(cor, n));
                m.empilhar(new CartaNumerica(cor, n));
            }
            m.empilhar(new CartaReverse(cor));
            m.empilhar(new CartaReverse(cor));
        }
        m.embaralhar();
        return m;
    }

    /** Move todas as cartas deste monte para destino (mantém ordem). */
    public void moverPara(Monte destino) {
        while (!cartas.isEmpty())
            destino.empilhar(cartas.removeLast()); // mantém ordem original
    }
}
