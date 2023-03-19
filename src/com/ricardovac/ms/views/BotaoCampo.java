package com.ricardovac.ms.views;

import com.ricardovac.ms.models.Campo;
import com.ricardovac.ms.models.CampoEvento;
import com.ricardovac.ms.models.CampoObservador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class BotaoCampo extends JButton implements CampoObservador, MouseListener {
    private final Color BG_PADRAO = new Color(184, 184, 184);
    private final Color BG_MARCAR = new Color(8, 179, 247);
    private final Color BG_EXPLODIR = new Color(189, 66, 68);
    private final Color TEXTO_VERDE = new Color(0, 100, 0);
    private Campo campo;

    public BotaoCampo(Campo campo) {
        this.campo = campo;
        setBackground(BG_PADRAO);
        setOpaque(true);
        setBorder(BorderFactory.createBevelBorder(0));

        addMouseListener(this);
        campo.registrarObservador(this);
    }

    @Override
    public void eventoOcorreu(Campo campo, CampoEvento evento) {
        switch (evento) {
            case ABRIR -> aplicarEstiloAbrir();
            case MARCAR -> aplicarEstiloMarcar();
            case EXPLODIR -> aplicarEstiloExplodir();
            default -> aplicarEstiloPadrao();
        }
    }

    private void aplicarEstiloPadrao() {
        setBackground(BG_PADRAO);
        setText("");
    }

    private void aplicarEstiloExplodir() {
        setBackground(BG_EXPLODIR);
        setForeground(Color.WHITE);
        setText("X");
    }

    private void aplicarEstiloMarcar() {
        setBackground(BG_MARCAR);
        setForeground(Color.BLACK);
        setText("M");
    }

    private void aplicarEstiloAbrir() {
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        if (campo.isMinado()) {
            setBackground(BG_EXPLODIR);
            return;
        }

        setBackground(BG_PADRAO);

        switch (campo.minasNaVizinhanca()) {
            case 1 -> setForeground(TEXTO_VERDE);
            case 2 -> setForeground(Color.BLUE);
            case 3 -> setForeground(Color.YELLOW);
            case 4, 5, 6 -> setForeground(Color.RED);
            default -> {
                return;
            }
        }
        String valor = !campo.vizinhancaSegura() ? campo.minasNaVizinhanca() + "" : "";
        setText(valor);
    }
    // Interface eventos do mouse;

    @Override
    public void mouseClicked(java.awt.event.MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == 1) {
            campo.abrir();
        } else {
            campo.alternarMarcacao();
        }
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent mouseEvent) {

    }
}