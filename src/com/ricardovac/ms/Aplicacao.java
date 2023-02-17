package com.ricardovac.ms;

import com.ricardovac.ms.models.Tabuleiro;
import com.ricardovac.ms.views.TabuleiroConsole;

public class Aplicacao {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 3);
        new TabuleiroConsole(tabuleiro);
    }
}