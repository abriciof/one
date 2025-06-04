package com.one.modelo.cartas;

public final class CartaNumerica extends Carta {
    private final int numero;

    public CartaNumerica(Cor cor, int numero) {
        super(cor);
        this.numero = numero;
    }
    public int getNumero() { return numero; }

    @Override
    public boolean podeSerJogadaSobre(Carta topo) {
        if (topo instanceof CartaNumerica){
            CartaNumerica cn = (CartaNumerica) topo;
            return getCor().equals(topo.getCor()) || getNumero() == cn.getNumero();
        }
        return getCor().equals(topo.getCor());   // topo é da mesma cor
    }

    @Override
    public String toString() {
        return numero + "-" + getCor();
    }
}
