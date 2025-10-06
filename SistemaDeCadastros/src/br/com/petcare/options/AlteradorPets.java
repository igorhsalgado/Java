package br.com.petcare.options;

import br.com.petcare.classes.Pet;

import java.util.List;
import java.util.Scanner;

public class AlteradorPets {
    private RealizadorCadastros validador = new RealizadorCadastros();

    // Executa a alteração escolhida pelo usuário
    public void executarAlteracao(Scanner sc, List<Pet> todosOsPets, GerenciadorDeArquivos criador) {
        if (todosOsPets.isEmpty()) {
            System.out.println("\nNão há pets cadastrados");
            return;
        }

        System.out.println("\n--- Etapa 1: Encontrar o Pet para Alterar ---");
        BuscadorDePets buscador = new BuscadorDePets();
        List<Pet> petsEncontrados = buscador.buscar(sc, todosOsPets);

        if (petsEncontrados != null) {
            buscador.exibirResultados(petsEncontrados);

            if (petsEncontrados.isEmpty()) {
                return;
            }

            System.out.println("\nDigite o número do pet que você deseja alterar (ou 0 para cancelar):");
            try {
                int escolha = Integer.parseInt(sc.nextLine());
                if (escolha == 0) {
                    System.out.println("Alteração cancelada");
                    return;
                } else if (escolha < 1 || escolha > petsEncontrados.size()) {
                    System.out.println("Número inválido. Operação cancelada.");
                    return;
                }
                Pet petParaAlterar = petsEncontrados.get(escolha - 1);
                editarDadosDoPet(sc, petParaAlterar);

                criador.atualizarPet(petParaAlterar);
                System.out.println("\nPet atualizado com sucesso!");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Operação cancelada.");
            }
        }
    }

    // Chama os metódos de edição e edita na prática cada uma das características
    private void editarDadosDoPet(Scanner sc, Pet pet) {
        System.out.println("\n--- Etapa 2: Editando Pet: " + pet.getNomeCompleto() + " ---");
        System.out.println("(Pressione Enter para manter o valor atual)");

        editarNome(sc, pet);
        editarRaca(sc, pet);
        editarIdade(sc, pet);
        editarPeso(sc, pet);
        editarEndereco(sc, pet);
    }

    /*
        A SEGUIR TEM TODOS OS MÉTODOS DE EDIÇÃO PARA CADA UMA DAS CARACTERÍSTICAS PERMITIDAS
     */

    private void editarNome(Scanner scanner, Pet pet) {
        System.out.printf("\n[1/5] Nome atual: %s\n", pet.getNomeCompleto());
        System.out.print("Digite o novo nome e sobrenome: ");
        String novoValor = scanner.nextLine();

        if (!novoValor.trim().isEmpty()) {
            if (validador.validar(novoValor, 1)) {
                pet.setNomeCompleto(novoValor.trim());
                System.out.println(" >> Nome atualizado!");
            } else {
                System.out.println(" >> Entrada inválida. O nome original será mantido.");
            }
        }
    }

    private void editarRaca(Scanner scanner, Pet pet) {
        System.out.printf("\n[2/5] Raça atual: %s\n", pet.getRaca());
        System.out.print("Digite a nova raça: ");
        String novoValor = scanner.nextLine();

        if (!novoValor.trim().isEmpty()) {
            if (validador.validar(novoValor, 7)) {
                pet.setRaca(novoValor.trim());
                System.out.println(" >> Raça atualizada!");
            } else {
                System.out.println(" >> Entrada inválida. A raça original será mantida.");
            }
        }
    }

    private void editarIdade(Scanner scanner, Pet pet) {
        System.out.printf("\n[3/5] Idade atual: %s\n", pet.getIdadeAproximada());
        System.out.print("Digite a nova idade em ANOS (ex: 0.5 para 6 meses): ");
        String novoValor = scanner.nextLine();

        if (!novoValor.trim().isEmpty()) {
            if (validador.validar(novoValor, 5)) {
                pet.setIdadeAproximada(novoValor.trim());
                System.out.println(" >> Idade atualizada!");
            } else {
                System.out.println(" >> Entrada inválida. A idade original será mantida.");
            }
        }
    }

    private void editarPeso(Scanner scanner, Pet pet) {
        System.out.printf("\n[4/5] Peso atual: %s\n", pet.getPesoAproximado());
        System.out.print("Digite o novo peso em kg (ex: 4.5): ");
        String novoValor = scanner.nextLine();

        if (!novoValor.trim().isEmpty()) {
            if (validador.validar(novoValor, 6)) {
                pet.setPesoAproximado(novoValor.trim());
                System.out.println(" >> Peso atualizado!");
            } else {
                System.out.println(" >> Entrada inválida. O peso original será mantido.");
            }
        }
    }

    private void editarEndereco(Scanner scanner, Pet pet) {
        System.out.printf("\n[5/5] Endereço atual: %s, %s, %s\n", pet.getRua(), pet.getNumeroCasa(), pet.getCidade());
        System.out.print("Digite o novo endereço (Formato: Rua, Número, Cidade): ");
        String novoValor = scanner.nextLine();

        if (!novoValor.trim().isEmpty()) {
            if (validador.validar(novoValor, 4)) {
                String[] partes = novoValor.split(",");
                String rua = partes[0].trim();
                String numero = partes[1].trim();
                String cidade = partes[2].trim();

                pet.setRua(rua.isEmpty() ? Pet.NAO_INFORMADO : rua);
                pet.setNumeroCasa(numero.isEmpty() ? Pet.NAO_INFORMADO : numero);
                pet.setCidade(cidade.isEmpty() ? Pet.NAO_INFORMADO : cidade);
                System.out.println(" >> Endereço atualizado!");
            } else {
                System.out.println(" >> Entrada inválida. O endereço original será mantido.");
            }
        }
    }

}
