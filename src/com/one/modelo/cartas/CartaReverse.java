package com.one.modelo.cartas;

public final class CartaReverse extends Carta {

    public CartaReverse(Cor cor) { super(cor); }

    @Override
    public boolean podeSerJogadaSobre(Carta topo) {
        return getCor().equals(topo.getCor()) || topo instanceof CartaReverse;
    }

    @Override
    public String toString() { return "REVERSE-" + getCor(); }
}
