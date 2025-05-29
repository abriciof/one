package com.one.modelo.cartas;

public abstract class Carta {
    private final Cor cor;

    protected Carta(Cor cor) { this.cor = cor; }

    public Cor getCor() { return cor; }

    public abstract boolean podeSerJogadaSobre(Carta topo);

    @Override
    public abstract String toString();
}
