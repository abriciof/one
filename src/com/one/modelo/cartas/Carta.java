package com.one.modelo.cartas;

import java.util.HashMap;
import java.util.Map;

public abstract class Carta {
    private final Cor cor;
    private static final Map<String, String[]> DESENHOS_INTERNOS_CARTAS = new HashMap<>();

    static {
        DESENHOS_INTERNOS_CARTAS.put("1", new String[]{" :/\\: ", " (__) "});
        DESENHOS_INTERNOS_CARTAS.put("2", new String[]{" (\\/) ", " :\\/: "});
        DESENHOS_INTERNOS_CARTAS.put("3", new String[]{" :(): ", " ()() "});
        DESENHOS_INTERNOS_CARTAS.put("4", new String[]{" :/\\: ", " :V: "});
        DESENHOS_INTERNOS_CARTAS.put("5", new String[]{" :/\\: ", " (__) "});
        DESENHOS_INTERNOS_CARTAS.put("6", new String[]{" (\\/) ", " :\\/: "});
        DESENHOS_INTERNOS_CARTAS.put("7", new String[]{" :(): ", " ()() "});
        DESENHOS_INTERNOS_CARTAS.put("8", new String[]{" :/\\: ", " :\\/: "});
        DESENHOS_INTERNOS_CARTAS.put("9", new String[]{" :/\\: ", " (__) "});
        DESENHOS_INTERNOS_CARTAS.put("0", new String[]{" :/\\: ", " :\\/: "});
        DESENHOS_INTERNOS_CARTAS.put("X", new String[]{" :/\\: ", " (__) "}); // Bloqueio
        DESENHOS_INTERNOS_CARTAS.put("R", new String[]{" :(): ", " ()() "}); // Reverso
    }
    protected Carta(Cor cor) { this.cor = cor; }

    public Cor getCor() { return cor; }

    public abstract boolean podeSerJogadaSobre(Carta topo);

    @Override
    public abstract String toString();

    public String desenharComoTexto() {
        String simboloPrincipal = this.toString();
        Cor corDaCarta = this.getCor();


        String[] partesDoDesenho = DESENHOS_INTERNOS_CARTAS.get(simboloPrincipal);
        String iconeLinha1 = "      ";
        String iconeLinha2 = "      ";

        if (partesDoDesenho != null && partesDoDesenho.length == 2) {
            iconeLinha1 = partesDoDesenho[0];
            iconeLinha2 = partesDoDesenho[1];
        } else {

            System.err.println("Aviso: Símbolo de carta '" + simboloPrincipal + "' não mapeado para desenho interno.");
        }


        String[] linhasDaCarta = new String[6];
        linhasDaCarta[0] = ".------.";

        linhasDaCarta[1] = String.format("|%1s.--. |", simboloPrincipal);
        linhasDaCarta[2] = String.format("|%s|", iconeLinha1);
        linhasDaCarta[3] = String.format("|%s|", iconeLinha2);
        linhasDaCarta[4] = String.format("| '--'%1s|", simboloPrincipal);
        linhasDaCarta[5] = "`------'";

        String cartaDesenhada = String.join("\n", linhasDaCarta);

        return corDaCarta.colorir(cartaDesenhada);
    }

    public String colorir(){
        Cor corAtual = this.getCor();
        return corAtual.colorir(this.toString());
    }

}
