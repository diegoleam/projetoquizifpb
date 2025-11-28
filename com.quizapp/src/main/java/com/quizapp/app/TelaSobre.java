package com.quizapp.app;

import javax.swing.*;
import java.awt.*;

public class TelaSobre extends JFrame {

    public TelaSobre() {
        setTitle("Sobre o Projeto");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Cabeçalho
        JLabel titleLabel = new JLabel("Projeto Simulado de Informática – Concurso Público");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Conteúdo
        JTextArea contentArea = new JTextArea();
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setFont(new Font("Arial", Font.PLAIN, 16));
        contentArea.setText(
                "Intuito do Projeto:\n" +
                        "O objetivo deste projeto é criar um simulado de informática voltado para concursos públicos, " +
                        "especialmente do IFPB e bancas IDECAN, VUNESP e FCC. " +
                        "O quiz permite que o usuário teste seus conhecimentos em informática, receba feedback, " +
                        "pontuação final e tenha a opção de reiniciar o teste.\n\n" +
                        "Membros da Equipe:\n" +
                        "• Diego Leam Salve de Sousa\n" +
                        "• Jose Jhonathan Ferreira de Sousa\n" +
                        "• Jose Anderson Araujo Lima\n" +
                        "• Simone do Nascimento"
        );

        JScrollPane scrollPane = new JScrollPane(contentArea);
        add(scrollPane, BorderLayout.CENTER);

        // Rodapé com botão de fechar
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton closeBtn = new JButton("Fechar");
        closeBtn.setFont(new Font("Arial", Font.BOLD, 14));
        closeBtn.addActionListener(e -> dispose());
        footer.add(closeBtn);
        add(footer, BorderLayout.SOUTH);

        // Definindo cores personalizadas Botao Fechar
        closeBtn.setBackground(new Color(231, 76, 60)); // Vermelho
        closeBtn.setForeground(Color.WHITE); // texto branco


        setVisible(true);
    }
}
