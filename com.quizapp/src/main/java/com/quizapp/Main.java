package com.quizapp;

import com.formdev.flatlaf.FlatLightLaf;
import com.quizapp.app.TelaInicial;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup(); // ATIVA O TEMA MODERNO
        javax.swing.SwingUtilities.invokeLater(() -> new TelaInicial());
    }
}
