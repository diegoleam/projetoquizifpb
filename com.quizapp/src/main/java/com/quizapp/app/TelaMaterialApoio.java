package com.quizapp.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URI;

public class TelaMaterialApoio extends JFrame {

    public TelaMaterialApoio() {
        setTitle("Material de Apoio - Provas Anteriores");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Tela já abre maximizada
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Painel principal com fundo gradiente
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                Color c1 = new Color(120, 81, 169); // roxo escuro
                Color c2 = new Color(180, 140, 220); // roxo claro

                g2d.setPaint(new GradientPaint(0, 0, c1, getWidth(), getHeight(), c2));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        setContentPane(mainPanel);

        // Título
        JLabel title = new JLabel("Provas Anteriores", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));

        mainPanel.add(title, BorderLayout.NORTH);

        // Painel central com botões
        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JButton btnProvas = createModernButton("Acessar Provas Anteriores");
        JButton btnVoltar = createModernButton("Voltar");

        // Ação do botão de provas
        btnProvas.addActionListener((ActionEvent e) -> abrirLink("https://www.pciconcursos.com.br/provas/if"));

        // Voltar para o menu
        btnVoltar.addActionListener((ActionEvent e) -> {
            dispose();
            new TelaInicial(); // ← sua tela inicial
        });

        center.add(btnProvas);
        center.add(Box.createVerticalStrut(30));
        center.add(btnVoltar);

        mainPanel.add(center, BorderLayout.CENTER);

        setVisible(true);
    }

    // BOTÃO MODERNO
    private JButton createModernButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 22));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(70, 45, 125)); // roxo elegante
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setPreferredSize(new Dimension(350, 70));
        btn.setMaximumSize(new Dimension(350, 70));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(90, 60, 150));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(70, 45, 125));
            }
        });

        return btn;
    }

    // Abrir link no navegador
    private void abrirLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Não foi possível abrir o link.");
        }
    }
}
