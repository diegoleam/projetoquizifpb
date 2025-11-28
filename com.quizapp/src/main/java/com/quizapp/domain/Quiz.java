package com.quizapp.domain;

import com.quizapp.model.Question;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections; // já que não está importado


// Classe que representa um quiz completo, com várias perguntas
public class Quiz {

    private final List<Question> questions; // Lista de todas as questões do quiz
    private final List<Integer> userAnswers; // Lista para armazenar as respostas do usuário
    private int currentIndex = 0; // Índice da questão atual
    private int score = 0; // Contador de acertos do usuário

    // Construtor que recebe a lista de questões
    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.userAnswers = new ArrayList<>();
        // Inicializa a lista de respostas do usuário com -1, indicando "não respondido"
        for (int i = 0; i < questions.size(); i++) {
            userAnswers.add(-1);
        }
    }

    public void shuffleQuestions() {
        // Embaralha a lista de questões
        Collections.shuffle(questions);

        // Reinicia índice e respostas
        currentIndex = 0;
        score = 0;
        for (int i = 0; i < userAnswers.size(); i++) {
            userAnswers.set(i, -1);
        }
    }

    // Retorna a questão atual do quiz
    public Question getCurrentQuestion() {
        if (currentIndex < questions.size()) return questions.get(currentIndex);
        return null; // Retorna nulo se não houver mais questões
    }

    // Registra a resposta do usuário para a questão atual
    public boolean answerCurrent(int chosenIndex) {
        Question q = getCurrentQuestion(); // pega a questão atual
        if (q == null) return false;

        userAnswers.set(currentIndex, chosenIndex); // salva a resposta do usuário
        boolean correct = q.getAnswerIndex() == chosenIndex; // verifica se está correta
        if (correct) score++; // aumenta pontuação se correto
        currentIndex++; // passa para a próxima questão
        return correct; // retorna se acertou ou não
    }

    // Verifica se ainda há questões restantes
    public boolean hasNext() {
        return currentIndex < questions.size();
    }

    // Retorna a pontuação atual do usuário
    public int getScore() {
        return score;
    }

    // Retorna o total de questões do quiz
    public int getTotal() {
        return questions.size();
    }

    // =============================
    // Métodos adicionais para exibir o gabarito
    // =============================

    // Retorna a lista de todas as questões
    public List<Question> getQuestions() {
        return questions;
    }

    // Retorna as respostas do usuário
    public List<Integer> getUserAnswers() {
        return userAnswers;
    }
}
