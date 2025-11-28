package com.quizapp.service;

// Importa classes necessárias do Gson para manipular JSON
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

// Importa a classe Question que será carregada do JSON
import com.quizapp.model.Question;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

// Classe responsável por carregar perguntas de um arquivo JSON
public class JsonLoader {

    // Método estático que carrega uma lista de questões a partir de um caminho de recurso
    public static List<Question> loadQuestions(String resourcePath) throws Exception {

        // Tenta abrir o recurso (arquivo JSON) dentro do projeto
        InputStream is = JsonLoader.class.getResourceAsStream(resourcePath);

        // Verifica se o arquivo foi encontrado; se não, lança exceção
        if (is == null) throw new IllegalArgumentException("Recurso não encontrado: " + resourcePath);

        // Cria um leitor de stream para ler o conteúdo do JSON em UTF-8
        InputStreamReader reader = new InputStreamReader(is, "UTF-8");

        // Define o tipo da lista que será lida do JSON (List<Question>)
        Type listType = new TypeToken<List<Question>>() {}.getType();

        // Converte o JSON para uma lista de objetos Question usando Gson
        List<Question> questions = new Gson().fromJson(reader, listType);

        // Fecha o leitor para liberar recursos
        reader.close();

        // Retorna a lista de questões carregadas
        return questions;
    }
}
