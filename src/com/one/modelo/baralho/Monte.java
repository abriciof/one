package com.one.modelo.baralho;

import com.one.modelo.cartas.*;
import java.util.*;

public class Monte {
    private final Deque<Carta> cartas = new ArrayDeque<>();

    public void empilhar(Carta c) { cartas.push(c); }

    public Carta comprar() { return cartas.pop(); }

    public Carta topo() { return cartas.peek(); }

    public int tamanho() { return cartas.size(); }

    public void embaralhar() {
        List<Carta> lista = new ArrayList<>(cartas);
        Collections.shuffle(lista);
        cartas.clear();
        lista.forEach(cartas::push);
    }

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

    public void moverPara(Monte destino) {
        while (!cartas.isEmpty())
            destino.empilhar(cartas.removeLast());
    }
}
