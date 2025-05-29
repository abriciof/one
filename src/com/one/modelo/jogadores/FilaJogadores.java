package com.one.modelo.jogadores;

import java.util.*;

public class FilaJogadores {
    private final List<Jogador> ordem = new ArrayList<>();
    private int indiceDaVez = 0;
    private int direcao = 1;   // 1 horário, -1 anti-horário

    public List<Jogador> getJogadores() {
        return Collections.unmodifiableList(ordem);
    }

    public void adicionar(Jogador j) { ordem.add(j); }

    public Jogador atual() { return ordem.get(indiceDaVez); }

    public void avancar() {
        indiceDaVez = (indiceDaVez + direcao + ordem.size()) % ordem.size();
    }

    public void inverterDirecao() { direcao *= -1; }
}
