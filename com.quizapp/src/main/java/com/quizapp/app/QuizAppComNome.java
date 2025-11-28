package com.quizapp.app;

public class QuizAppComNome {

    public static String jogadorNome = "";

    public QuizAppComNome(String nome) {
        jogadorNome = nome;
        new TelaSimulado(nome); // abre o quiz original
    }
}
