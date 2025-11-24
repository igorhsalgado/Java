package br.com.faesa.services;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivos {
    private static final String DIR = "dados"; 

    public GerenciadorArquivos() {
        try {
            Files.createDirectories(Paths.get(DIR));
        } catch (IOException e) {
            System.err.println("Erro ao criar diretório de dados: " + e.getMessage());
        }
    }

    public void salvarLinha(String arquivo, String linha) {
        try {
            Path path = Paths.get(DIR, arquivo);
        
            Files.writeString(path, linha + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public void salvarTodasLinhas(String arquivo, List<String> linhas) {
        try {
            Path path = Paths.get(DIR, arquivo);
            Files.write(path, linhas, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Erro ao atualizar arquivo: " + e.getMessage());
        }
    }

    public List<String> lerArquivo(String arquivo) {
        Path path = Paths.get(DIR, arquivo);
        if (!Files.exists(path)) return new ArrayList<>();
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
