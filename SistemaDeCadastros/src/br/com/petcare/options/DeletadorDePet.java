package br.com.petcare.options;

import br.com.petcare.classes.Pet;

import java.util.List;
import java.util.Scanner;

public class DeletadorDePet {

    // Faz a busca do pet se e deleta o arquivo txt do banco de dados
    public void executarDelecao (Scanner sc, List<Pet> todosOsPets, GerenciadorDeArquivos criador) {
        if (todosOsPets.isEmpty()) {
            System.out.println("\nNão há pets cadastrados para deletar.");
            return;
        }

        System.out.println("\n--- Etapa 1: Encontrar o pet para deletar ---");
        BuscadorDePets buscador = new BuscadorDePets();
        List<Pet> petsEncontrados = buscador.buscar(sc, todosOsPets);
        buscador.exibirResultados(petsEncontrados);

        if (petsEncontrados.isEmpty()) {
            return;
        }

        System.out.print("\nDigite o número do pet que você deseja DELETAR (ou 0 para cancelar): ");
        Pet petParaDeletar = null;
        try {
            int escolha = Integer.parseInt(sc.nextLine());
            if (escolha == 0) {
                System.out.println("Deleção cancelada.");
                return;
            }
            if (escolha < 1 || escolha > petsEncontrados.size()) {
                System.out.println("Número inválido. Operação cancelada.");
                return;
            }
            petParaDeletar = petsEncontrados.get(escolha - 1);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Operação cancelada.");
            return;
        }

        System.out.println("\n--- Etapa 2: Confirmação ---");
        System.out.printf("Você tem certeza que deseja deletar permanentemente '%s'?\n", petParaDeletar.getNomeCompleto());
        System.out.print("Digite SIM para confirmar: ");
        String confirmacao = sc.nextLine();

        if (confirmacao.equalsIgnoreCase("SIM")) {
            criador.deletarPet(petParaDeletar);

            todosOsPets.remove(petParaDeletar);

            System.out.println("\n>> Pet deletado com sucesso! <<");
        } else {
            System.out.println("\nDeleção cancelada pelo usuário.");
        }
    }
}