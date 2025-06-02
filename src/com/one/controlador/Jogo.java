package com.one.controlador;

import com.one.modelo.baralho.Monte;
import com.one.modelo.cartas.Carta;
import com.one.modelo.cartas.CartaReverse;
import com.one.modelo.jogadores.*;

public class Jogo {
    private final Monte compra = Monte.gerarBaralhoPadrao();
    private final Monte descarte = new Monte();
    private final ListaJogadores lista = new ListaJogadores();

    public Jogo(String... nomesJogadores) {
        for (String nome : nomesJogadores) lista.adicionar(new Jogador(nome));
    }

    private void distribuirCartasIniciais() {
        for (int i = 0; i < 7; i++) {
            for (Jogador j : lista.getJogadores()) {
                j.getMao().adicionar(compra.comprar());
            }
        }
    }

    public void iniciar() {
        System.out.println("\tONE!\n");

        distribuirCartasIniciais();
        Carta primeira = compra.comprar();
        while (primeira instanceof CartaReverse) {
            compra.empilhar(primeira); compra.embaralhar();
            primeira = compra.comprar();
        }
        System.out.println("Carta Inicial: " + primeira.colorir() + "\n");
        descarte.empilhar(primeira);

        while (true) {
            Jogador j = lista.atual();
            int resultado = j.jogarTurno(compra, descarte);
            if (j.getMao().tamanho() == 1 && Math.random() < 0.5) {
                j.declararUno();
                System.out.println("\t[!] " + j.getNome() + " GRITOU ONE!");
            }
            if (j.deveSerPenalizadoPorNaoDeclararUno()) {
                System.out.println("	[!] " + j.getNome() + " esqueceu de GRITAR ONE! Comprando 2 cartas de penalidade.");
                j.getMao().adicionar(compra.comprar());
                j.getMao().adicionar(compra.comprar());
                j.resetarDeclaracaoUno();
                lista.avancar();
                continue;
            }
            j.resetarDeclaracaoUno();

            // Verificar se a carta do topo é um bloqueio
            if (descarte.topo() instanceof com.one.modelo.cartas.CartaBloqueio) {
                System.out.println("\t[!] " + j.getNome() + " jogou BLOQUEIO! Pulando o proximo jogador.");
                lista.avancar(); // pular o próximo jogador
            }

            if (j.getMao().tamanho() == 0) {
                System.out.println("\n>>> " + j.getNome() + " VENCEU! <<<");
                break;
            }
            if (resultado == 1) lista.inverterDirecao();
            lista.avancar();
        }
    }
}
