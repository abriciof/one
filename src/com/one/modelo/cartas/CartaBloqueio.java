package com.one.modelo.cartas;

public class CartaBloqueio extends Carta {
    public CartaBloqueio(Cor cor) {
        super(cor);
    }

    @Override
    public boolean podeSerJogadaSobre(Carta outra) {
        return this.getCor().equals(outra.getCor()) || outra instanceof CartaBloqueio;
    }

    @Override
    public String toString() {
        return getCor() + " BLOQUEIO";
    }
}