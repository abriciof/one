package com.one.modelo.cartas;

public final class CartaBloqueio extends Carta {
    public CartaBloqueio(Cor cor) {
        super(cor);
    }

    @Override
    public boolean podeSerJogadaSobre(Carta topo) {
        return getCor().equals(topo.getCor()) || topo instanceof CartaBloqueio;
    }

    @Override
    public String toString() {
        return "BLOQUEIO-" + getCor();
    }
}