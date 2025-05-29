package com.one.modelo.cartas;

public final class CartaReverse extends Carta {

    public CartaReverse(Cor cor) { super(cor); }

    @Override
    public boolean podeSerJogadaSobre(Carta topo) {
        return getCor() == topo.getCor() || topo instanceof CartaReverse;
    }

    @Override
    public String toString() { return getCor() + "-REVERSE"; }
}
