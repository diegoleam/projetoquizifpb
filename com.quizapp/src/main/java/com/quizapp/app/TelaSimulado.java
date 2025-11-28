package com.quizapp.app;

import com.quizapp.domain.Quiz;
import com.quizapp.model.Question;
import com.quizapp.service.JsonLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.List;

public class TelaSimulado extends JFrame {

    private Quiz quiz;
    private JPanel cardPanel;
    private final String playerName;
    private long startTime;
    private JLabel timerLabel;
    private Timer timer;

    public TelaSimulado(String playerName) {
        this.playerName = playerName;

        setTitle("Simulado de Informática - Concurso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com gradiente roxo
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(120, 81, 169);  // roxo escuro
                Color color2 = new Color(180, 140, 220); // roxo claro
                g2d.setPaint(new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        setContentPane(mainPanel);

        try {
            List<Question> questions = JsonLoader.loadQuestions("/listaquestoes.json");
            Collections.shuffle(questions); // embaralha as questões na primeira execução
            quiz = new Quiz(questions);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro carregando perguntas: " + e.getMessage());
            return;
        }

        startTime = System.currentTimeMillis();

        mainPanel.add(buildHeader(), BorderLayout.NORTH);
        mainPanel.add(buildFooter(), BorderLayout.SOUTH);

        cardPanel = new JPanel(new CardLayout());
        cardPanel.setOpaque(false);
        buildQuestionCards();
        mainPanel.add(cardPanel, BorderLayout.CENTER);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        startTimer();
        setVisible(true);
    }

    private JPanel buildHeader() {
        JLabel title = new JLabel("Simulado de Informática – Concurso Público", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.add(title, BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildFooter() {
        JLabel nameLabel = new JLabel("Candidato: " + playerName);
        nameLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        nameLabel.setForeground(Color.WHITE);

        JLabel timerTitle = new JLabel("Tempo de prova: ");
        timerTitle.setFont(new Font("Arial", Font.BOLD, 14));
        timerTitle.setForeground(Color.WHITE);

        timerLabel = new JLabel("00:00");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        timerLabel.setForeground(Color.WHITE);

        JPanel timerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        timerPanel.setOpaque(false);
        timerPanel.add(timerTitle);
        timerPanel.add(timerLabel);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        panel.add(nameLabel, BorderLayout.WEST);
        panel.add(timerPanel, BorderLayout.EAST);

        return panel;
    }

    private void startTimer() {
        timer = new Timer(1000, e -> {
            long elapsedMillis = System.currentTimeMillis() - startTime;
            long seconds = (elapsedMillis / 1000) % 60;
            long minutes = (elapsedMillis / 1000) / 60;
            timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
        });
        timer.start();
    }

    private void buildQuestionCards() {
        int index = 0;
        while (quiz.hasNext()) {
            Question q = quiz.getCurrentQuestion();
            JPanel panel = createQuestionPanel(q, index);
            cardPanel.add(panel, "Q" + index);
            index++;
            quiz.answerCurrent(-1);
        }
        try {
            List<Question> questions = JsonLoader.loadQuestions("/listaquestoes.json");
            quiz = new Quiz(questions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JPanel createQuestionPanel(Question q, int index) {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panel.setOpaque(false);

        JLabel questionLabel = new JLabel("<html>Q" + (index + 1) + ": " + q.getQuestion() + "</html>");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setForeground(Color.WHITE);
        panel.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setOpaque(false);

        ButtonGroup group = new ButtonGroup();
        JRadioButton[] buttons = new JRadioButton[q.getOptions().size()];
        for (int i = 0; i < q.getOptions().size(); i++) {
            buttons[i] = new JRadioButton((char) ('A' + i) + ") " + q.getOptions().get(i));
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setOpaque(false);
            group.add(buttons[i]);
            optionsPanel.add(buttons[i]);
            optionsPanel.add(Box.createVerticalStrut(10));
        }
        panel.add(optionsPanel, BorderLayout.CENTER);

        JButton nextBtn = new JButton(index == quiz.getTotal() - 1 ? "Finalizar" : "Próxima");
        nextBtn.setFont(new Font("Arial", Font.BOLD, 20));
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setBackground(new Color(46, 204, 113));
        nextBtn.setFocusPainted(false);
        nextBtn.setBorderPainted(false);
        nextBtn.setOpaque(true);
        nextBtn.setContentAreaFilled(true);
        nextBtn.setPreferredSize(new Dimension(180, 50));

        nextBtn.addActionListener((ActionEvent e) -> {
            int chosen = -1;
            for (int i = 0; i < buttons.length; i++)
                if (buttons[i].isSelected()) chosen = i;

            if (chosen == -1) {
                JOptionPane.showMessageDialog(this, "Selecione uma alternativa.");
                return;
            }

            quiz.answerCurrent(chosen);

            if (!quiz.hasNext()) {
                showResult();
            } else {
                CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.next(cardPanel);
            }
        });

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setOpaque(false);
        bottom.add(nextBtn);
        panel.add(bottom, BorderLayout.SOUTH);

        return panel;
    }

    private void showResult() {
        timer.stop();

        int score = quiz.getScore();
        int total = quiz.getTotal();

        // =============== NOVO: porcentagem =================
        double porcentagem = ((double) score / total) * 100.0;
        String porcentagemFmt = String.format("%.2f", porcentagem);

        // Mensagem motivacional
        String status;
        if (porcentagem >= 60) {
            status = "Você está dentro da média para aprovação! Continue assim!";
        } else {
            status = "A performance está abaixo da média. Tente revisar os conteúdos.";
        }
        // ====================================================

        long durationMillis = System.currentTimeMillis() - startTime;
        long seconds = (durationMillis / 1000) % 60;
        long minutes = (durationMillis / 1000) / 60;

        String result = String.format(
                "%s, você acertou %d de %d.\nPorcentagem: %s%%\n\n%s\n\nTempo total: %d min %d seg",
                playerName, score, total, porcentagemFmt, status, minutes, seconds
        );

        StringBuilder gabarito = new StringBuilder();
        List<Question> questions = quiz.getQuestions();
        List<Integer> answers = quiz.getUserAnswers();

        for (int i = 0; i < total; i++) {
            Question q = questions.get(i);
            int correct = q.getAnswerIndex();
            int user = answers.get(i);

            gabarito.append(String.format(
                    "Q%d: %s\nSua resposta: %s\nCorreta: %s\n\n",
                    i + 1,
                    q.getQuestion(),
                    user == -1 ? "Não respondeu" : q.getOptions().get(user),
                    q.getOptions().get(correct)
            ));
        }

        JTextArea area = new JTextArea(result + "\n\n" + gabarito);
        area.setEditable(false);
        area.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(650, 450));

        int option = JOptionPane.showOptionDialog(
                this,
                scroll,
                "Resultado e Gabarito",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Fechar", "Reiniciar Quiz"},
                "Fechar"
        );

        if (option == 1) {
            dispose();
            try {
                List<Question> newQuestions = JsonLoader.loadQuestions("/listaquestoes.json");
                Collections.shuffle(newQuestions);
                quiz = new Quiz(newQuestions);
                new TelaSimulado(playerName);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao reiniciar quiz: " + e.getMessage());
            }
        } else {
            System.exit(0);
        }
    }
}
