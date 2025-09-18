package br.com.petcare.options;

import br.com.petcare.classes.Pet;
import br.com.petcare.enums.SexoPets;
import br.com.petcare.enums.TipoPets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class RealizadorCadastros {

    private static final String NOME_ARQUIVO_FORMULARIO = "formulario.txt";

    public Pet executar() {
        Pet petEmConstrucao = new Pet();
        Scanner scanner = new Scanner(System.in);

        try {
            List<String> todasAsLinhas = Files.readAllLines(Paths.get(NOME_ARQUIVO_FORMULARIO));
            System.out.println("--- Início do Processo de Cadastro ---");

            for (String linha : todasAsLinhas) {
                int numeroDaPergunta;
                String pergunta;

                try {
                    String[] partes = linha.split("-", 2);
                    numeroDaPergunta = Integer.parseInt(partes[0].trim());
                    pergunta = partes[1].trim();
                } catch (Exception e) {
                    System.out.println("Aviso: Pulando linha mal formatada no formulário: " + linha);
                    continue;
                }

                String resposta;
                while (true) {
                    System.out.println(pergunta);
                    System.out.println("Sua resposta: ");
                    resposta = scanner.nextLine();

                    boolean respostaValida = validar(resposta, numeroDaPergunta);

                    if (respostaValida) {
                        break;
                    }
                }

                switch (numeroDaPergunta) {
                    case 1: // Nome
                        petEmConstrucao.setNomeCompleto(confirmadorInformado(resposta));
                        break;
                    case 2: // Tipo
                        petEmConstrucao.setTipo(TipoPets.valueOf(resposta.trim().toUpperCase()));
                        break;
                    case 3: // Sexo
                        petEmConstrucao.setSexo(SexoPets.valueOf(resposta.trim().toUpperCase()));
                        break;
                    case 4: // Endereço - sendo dividido em 3
                        String[] partes = resposta.split(",", 3);

                        String rua = (partes.length > 0) ? partes[0].trim() : "";
                        String numero = (partes.length > 1) ? partes[1].trim() : "";
                        String cidade = (partes.length > 2) ? partes[2].trim() : "";

                        petEmConstrucao.setRua(rua.isEmpty() ? Pet.NAO_INFORMADO : rua);
                        petEmConstrucao.setNumeroCasa(numero.isEmpty() ? Pet.NAO_INFORMADO : numero);
                        petEmConstrucao.setCidade(cidade.isEmpty() ? Pet.NAO_INFORMADO : cidade);
                        break;
                    case 5: // Idade
                        petEmConstrucao.setIdadeAproximada(confirmadorInformado(resposta));
                        break;
                    case 6: // Peso
                        petEmConstrucao.setPesoAproximado(confirmadorInformado(resposta));
                        break;
                    case 7: // Raça
                        petEmConstrucao.setRaca(confirmadorInformado(resposta));
                        break;
                }
            }

            System.out.println("\n--- Cadastro Concluido com Sucesso! ---");
            return petEmConstrucao;

        } catch (IOException e) {
            System.err.println("Erro crítico ao ler o arquivo do formulário: " + e.getMessage());
            return null;
        }
    }

    // Criado visando simplificar o código e evitar a repetição no metódo executar
    private String confirmadorInformado (String resposta){
        return resposta.trim().isEmpty() ? Pet.NAO_INFORMADO : resposta.trim();
    }

    public boolean validar(String resposta, int numeroDaPergunta) {
        switch (numeroDaPergunta) {
            case 1: // Nome e Sobrenome - Sendo dividido em 2 para confirmar
                if (resposta == null || resposta.trim().isEmpty() || resposta.trim().split("\\s+").length < 2) {
                    System.out.println(">> ERRO: Por favor, insira o nome e sobrenome. Tente novamente.");
                    return false;
                }

                String regexNomeValido = "^[a-zA-ZÀ-ú\\s'-]+$";
                if (!resposta.trim().matches(regexNomeValido)) {
                    System.out.println(">> ERRO: O nome contém caracteres inválidos.");
                    return false;
                }
                return true;

            case 2: // Tipo
                try {
                    TipoPets.valueOf(resposta.trim().toUpperCase());
                    return true;
                } catch (IllegalArgumentException e) {
                    System.out.println(">> ERRO: O tipo deve ser 'Cachorro' ou 'Gato'.");
                    return false;
                }

            case 3: // Sexo
                try {
                    SexoPets.valueOf(resposta.trim().toUpperCase());
                    return true;
                } catch (IllegalArgumentException e) {
                    System.out.println(">> ERRO: O sexo deve ser 'Macho' ou 'Femea'.");
                    return false;
                }

            case 4: // Endereço
                String respostaCompleta = resposta.trim();

                String[] partes = respostaCompleta.split(",");
                if (partes.length != 3) {
                    System.out.println(">> ERRO: Formato inválido. Use o formato: Rua, Número, Cidade (Separados por vírgula)");
                    return false;
                }

                String rua = partes [0].trim();
                String numero = partes [1].trim();
                String cidade = partes [2].trim();

                if (rua.isEmpty() || numero.isEmpty() || cidade.isEmpty()) {
                    System.out.println(">> ERRO: Nenhuma parte do endereço (Rua, Número, Cidade) pode estar vázia.");
                    return false;
                }

                return true;

            case 5: // Idade
                try {
                    double idade = Double.parseDouble(resposta.replace(",", "."));
                    if (idade < 0 || idade > 20) {
                        System.out.println(">> ERRO: Idade inválida. Tente novamente.");
                        return false;
                    }
                    return true;
                } catch (NumberFormatException e) {
                    System.out.println(">> ERRO: A idade deve ser um número. Tente novamente.");
                    return false;
                }

            case 6: // Peso
                try {
                    double peso = Double.parseDouble(resposta.replace(",", "."));
                    if (peso < 0.5 || peso > 60) {
                        System.out.println(">> ERRO: Peso inválido. Tente novamente.");
                        return false;
                    }
                    return true;
                } catch (NumberFormatException e) {
                    System.out.println(">> ERRO: O peso deve ser um número. Tente novamente.");
                    return false;
                }

            case 7: // Raça
                String regexRacaValida = "^[a-zA-ZÀ-ú\\s'-]+$";
                if (!resposta.trim().matches(regexRacaValida)) {
                    System.out.println(">> ERRO: A raça contém caracteres inválidos.");
                    return false;
                }
                return true;

            default:
                return true;
        }
    }
}