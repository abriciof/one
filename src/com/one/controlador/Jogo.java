package com.one.controlador;

import com.one.modelo.baralho.Monte;
import com.one.modelo.cartas.*;
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
        distribuirCartasIniciais();
        Carta primeira = compra.comprar();

        // para primeira carta ser numérica
        while (!(primeira instanceof CartaNumerica)) {
            compra.empilhar(primeira);
            compra.embaralhar();
            primeira = compra.comprar();
        }

        System.out.println(
            primeira.getCor().colorir(
                "   ___    _  _     ___   \n" +
                "  / _ \\  | \\| |   | __|  \n" +
                " | (_) | | .` |   | _|   \n" +
                "  \\___/  |_|\\_|   |___|  \n" +
                "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n" +
                "\"`-0-0-'\"`-0-0-'\"`-0-0-' \n"
            )
        );

        System.out.println("[INÍCIO] Carta inicial " + primeira.colorir());
        descarte.empilhar(primeira);

        while (true) {
            Jogador jogador = lista.atual();
            int resultado = jogador.jogarTurno(compra, descarte);

            if (jogador.getMao().tamanho() == 0) {
                System.out.println("[FIM] Fim de Jogo!");
                System.out.println("\n>>> " + jogador.getNome() + " VENCEU! <<<");
                break;
            }

            switch (resultado) {
                case 1:
                    lista.inverterDirecao();
                    break;
                case 2:
                    lista.avancar();
                    Jogador pulado = lista.atual();
                    System.out.println("[PENALIDADE] " + pulado.getNome() + " sofreu um bloqueio!");
                    break;
                default:
                    break;
            }

            lista.avancar();
        }
    }
}
