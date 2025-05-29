package com.one.modelo.baralho;

import com.one.modelo.cartas.Carta;
import java.util.*;

public class Mao {
    private final List<Carta> cartas = new ArrayList<>();

    public void adicionar(Carta c) { cartas.add(c); }

    public int tamanho() { return cartas.size(); }

    /** Procura primeira carta jogável e retorna; senão null. */
    public Carta jogarCartaValida(Carta topo) {
        for (int i = 0; i < cartas.size(); i++) {
            if (cartas.get(i).podeSerJogadaSobre(topo))
                return cartas.remove(i);
        }
        return null;
    }

    @Override
    public String toString() { return cartas.toString(); }
}
