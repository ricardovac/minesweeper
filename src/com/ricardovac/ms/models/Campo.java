package com.ricardovac.ms.models;

import com.ricardovac.ms.exceptions.ExplosaoException;

import java.util.ArrayList;
import java.util.List;

public class Campo {
    private final int linha;
    private final int coluna;

    private boolean aberto = false;
    private boolean minado = false;
    private boolean marcado = false;

    private List<Campo> vizinhos = new ArrayList<>();

    Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    boolean adicionarVizinho(Campo vizinho) {
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = linha != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaColuna + deltaLinha;

        if (deltaGeral == 1 && !diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else if (deltaGeral == 2 & diagonal){
            vizinhos.add(vizinho);
            return true;
        } else {
            return true;
        }
    }

    void alternarMarcacao() {
        if(!aberto) {
            marcado = !marcado;
        }
    }

    boolean abrir() {
        if(!aberto && !marcado) {
            aberto = true;
            // Verifica se está minado
            if(minado) {
                throw new ExplosaoException();
            }
            if(vizinhancaSegura()) {
               vizinhos.forEach(v -> v.abrir());
            }

            return true;
        }
        return false;
    }

    boolean vizinhancaSegura() {
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    void minar() {
        minado = true;
    }

    public boolean isMarcado() {
       return marcado;
    }

    public boolean isAberto() {
        return aberto;
    }
}
