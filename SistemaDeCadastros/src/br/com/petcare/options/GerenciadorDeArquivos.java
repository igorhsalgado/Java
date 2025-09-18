package br.com.petcare.options;

import br.com.petcare.classes.Pet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GerenciadorDeArquivos {
    private static final Path ARQUIVO_PETS = Paths.get("C:\\Users\\Wcom\\Documents\\java\\SistemasDeCadastros" +
            "\\PetShop\\petsCadastros\\");

    // Gerando nome do arquivo se baseando na hora que foi finalizado o cadastro do pet, e o nome do mesmo
    private String gerarNomeArquivo (Pet pet) {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String timestamp = agora.format(formatoData);

        String nomePet = pet.getNomeCompleto()
                .toUpperCase()
                .replace(" ", "")
                .replaceAll("[^a-zA-Z0-9+\\-+]", "");

        return timestamp + "-" + nomePet + ".txt";
    }

    // Salvando o pet em formato txt
    public void salvarPet (Pet pet) {
        try {
            Files.createDirectories(ARQUIVO_PETS);
        } catch (IOException e) {
            System.err.println("Erro critico ao criar o diretório: "+ e.getMessage());
            return;
        }

        String nomeArquivo = gerarNomeArquivo(pet);
        Path caminhoArquivo = ARQUIVO_PETS.resolve(nomeArquivo);
        String conteudo = pet.gerarConteudo();

        try {
            Files.writeString(caminhoArquivo, conteudo);
            pet.setCaminhoDoArquivoOriginal(caminhoArquivo);
            System.out.println("Pet salvo com sucesso!");
            System.out.println("Ficha salva em: " + caminhoArquivo.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Erro ao salvar a ficha do pet: "+ e.getMessage());
        }
    }

    // Carregando todos os pets para servir de banco de dados para o sistema
    public List<Pet> carregarPets() {
        List<Pet> petsCadastrados = new ArrayList<>();

        if (!Files.exists(ARQUIVO_PETS)) {
            System.out.println("Diretório de cadastros não encontrado. Começando com uma lista vazia.");
            return petsCadastrados;
        }

        try (Stream<Path> streamDeArquivos = Files.list(ARQUIVO_PETS)) {

            streamDeArquivos
                    .filter(path -> path.toString().endsWith(".txt"))
                    .forEach(arquivo -> {
                        try {
                            List<String> linhas = Files.readAllLines(arquivo);
                            Pet pet = Pet.fromFileLines(linhas);
                            pet.setCaminhoDoArquivoOriginal(arquivo);
                            petsCadastrados.add(pet);

                        } catch (Exception e) {
                            System.err.println("!! Erro ao processar o arquivo: " + arquivo.getFileName()
                                    + ". O arquivo pode estar corrompido. Erro: " + e.getMessage());
                        }
                    });
        } catch (IOException e) {
            System.err.println("!! Erro crítico ao tentar listar os arquivos de pets: " + e.getMessage());
        }
        System.out.println(petsCadastrados.size() + " pet(s) carregado(s) do sistema.");
        return petsCadastrados;
    }

    // Atualizando o arquivo do pet
    public void atualizarPet(Pet petParaAtualizar) {
        try {
            if (petParaAtualizar.getCaminhoDoArquivoOriginal() != null) {
                Files.deleteIfExists(petParaAtualizar.getCaminhoDoArquivoOriginal());
            }
        } catch (IOException e) {
            System.err.println("Aviso: Não foi possível deletar o arquivo antigo. Um novo arquivo será criado.");
        }

        salvarPet(petParaAtualizar);
    }

    // Deletando o pet
    public void deletarPet (Pet petParaDeletar) {
        if (petParaDeletar.getCaminhoDoArquivoOriginal() == null) {
            System.err.println(">> ERRO: Não foi possivel encontrar o arquivo original do pet para deletar.");
            return;
        }

        try {
            Files.deleteIfExists(petParaDeletar.getCaminhoDoArquivoOriginal());
        } catch (IOException e) {
            System.err.println("Erro ao tentar deletar o arquivo: " + e.getMessage());
        }
    }
}