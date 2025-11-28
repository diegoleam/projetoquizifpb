package com.quizapp.app;

import javax.swing.*;
import java.awt.*;

public class TelaInicial {

    private final JFrame frame;

    public TelaInicial() {
        frame = new JFrame("SIMULAIF– Início");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        // ===== Painel com gradiente =====
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(75, 0, 130);   // roxo escuro
                Color color2 = new Color(138, 43, 226); // roxo claro
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        gradientPanel.setLayout(new BorderLayout(10, 10));
        frame.setContentPane(gradientPanel);

        // ===== Cabeçalho =====
        JLabel title = new JLabel("Bem-vindo ao Simulado de Informática!", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        title.setForeground(Color.WHITE);
        gradientPanel.add(title, BorderLayout.NORTH);

        // ===== Painel central =====
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false); // deixa transparente para ver o gradiente

        JLabel lbl = new JLabel("DIGITE SEU NOME");
        lbl.setFont(new Font("Arial", Font.PLAIN, 18));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbl.setForeground(Color.WHITE);
        centerPanel.add(lbl);
        centerPanel.add(Box.createVerticalStrut(10));

        JTextField txtNome = new JTextField();
        txtNome.setMaximumSize(new Dimension(300, 40));
        txtNome.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txtNome.setHorizontalAlignment(SwingConstants.CENTER);
        txtNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(txtNome);
        centerPanel.add(Box.createVerticalStrut(20));

        // Botão central Iniciar Quiz
        JButton startBtn = new JButton("Iniciar");
        startBtn.setFont(new Font("Arial", Font.BOLD, 18));
        startBtn.setBackground(new Color(138, 43, 226));
        startBtn.setForeground(Color.WHITE);
        startBtn.setFocusPainted(false);
        startBtn.setBorderPainted(false);
        startBtn.setOpaque(true);
        startBtn.setPreferredSize(new Dimension(200, 50));
        startBtn.setMaximumSize(new Dimension(200, 50));
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        startBtn.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Digite seu nome para continuar.");
                return;
            }
            frame.dispose();
            new TelaSimulado(nome);
        });

        centerPanel.add(startBtn);

        gradientPanel.add(centerPanel, BorderLayout.CENTER);

        // ===== Rodapé com botões =====
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setOpaque(false);

        JButton aboutBtn = new JButton("Sobre");
        styleButton(aboutBtn);
        aboutBtn.addActionListener(e -> new TelaSobre());

        JButton materialBtn = new JButton("Provas");
        styleButton(materialBtn);
        materialBtn.addActionListener(e -> new TelaMaterialApoio());

        bottomPanel.add(aboutBtn);
        bottomPanel.add(materialBtn);

        gradientPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Método para padronizar os botões
    private void styleButton(JButton btn) {
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBackground(new Color(123, 104, 238));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setPreferredSize(new Dimension(160, 40));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaInicial::new);
    }
}
