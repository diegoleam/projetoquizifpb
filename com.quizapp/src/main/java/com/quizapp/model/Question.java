package com.quizapp.model;
// Define o pacote onde a classe está localizada. Ajuda a organizar o projeto.

import java.util.List;
// Importa a interface List, usada para armazenar múltiplas opções de resposta da questão.

public class Question {
    // Classe que representa uma questão do quiz.

    private int id;
    // Identificador único da questão. Útil para diferenciar questões, especialmente se forem salvas em banco ou JSON.

    private String question;
    // Texto da pergunta que será exibida ao usuário.

    private List<String> options;
    // Lista de opções de resposta da pergunta. Pode ter 4, 5 ou mais alternativas.

    private int answerIndex;
    // Índice (posição) da resposta correta dentro da lista 'options'.
    // Ex: se a resposta correta é a primeira opção, o valor é 0.

    public Question() {}
    // Construtor vazio. Necessário para bibliotecas como Gson, que criam objetos dinamicamente ao ler JSON.

    // ===== GETTERS E SETTERS =====
    // Métodos usados para acessar e modificar os atributos privados.

    public int getId() { return id; }
    // Retorna o ID da questão.

    public void setId(int id) { this.id = id; }
    // Define o ID da questão.

    public String getQuestion() { return question; }
    // Retorna o texto da pergunta.

    public void setQuestion(String question) { this.question = question; }
    // Define o texto da pergunta.

    public List<String> getOptions() { return options; }
    // Retorna a lista de opções de resposta.

    public void setOptions(List<String> options) { this.options = options; }
    // Define a lista de opções de resposta.

    public int getAnswerIndex() { return answerIndex; }
    // Retorna o índice da resposta correta.

    public void setAnswerIndex(int answerIndex) { this.answerIndex = answerIndex; }
    // Define qual é a opção correta, usando o índice da lista.
}
