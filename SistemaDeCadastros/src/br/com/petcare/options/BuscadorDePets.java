package br.com.petcare.options;

import br.com.petcare.classes.Pet;
import br.com.petcare.enums.SexoPets;
import br.com.petcare.enums.TipoPets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BuscadorDePets {

    // Metódo para executar a busca do pet -> Responsabilidade apenas de buscar e mostrar
    public void executarBusca(Scanner scanner, List<Pet> todosOsPets) {
        List<Pet> petsEncontrados = buscar(scanner, todosOsPets);

        if (petsEncontrados != null ) {
            exibirResultados(petsEncontrados);
        }
    }

    // A busca propriamente dita do pet -> Responsabilidade de encontrar e >retornar< os dados
    public List<Pet> buscar(Scanner scanner, List<Pet> todosOsPets) {
        if (todosOsPets.isEmpty()) {
            return Collections.emptyList();
        }

        System.out.print("\nQual o tipo de animal que deseja buscar (Cachorro/Gato)? ");
        String tipoInput = scanner.nextLine().trim().toUpperCase();

        TipoPets tipoBuscado;
        try {
            tipoBuscado = TipoPets.valueOf(tipoInput);
        } catch (IllegalArgumentException e) {
            System.out.println(">> ERRO: Tipo inválido. Busca cancelada.");
            return Collections.emptyList();
        }

        List<Pet> petsFiltradosPorTipo = new ArrayList<>();
        for (Pet pet : todosOsPets) {
            if (pet.getTipo() == tipoBuscado) {
                petsFiltradosPorTipo.add(pet);
            }
        }

        if (petsFiltradosPorTipo.isEmpty()) {
            System.out.println("Nenhum " + tipoInput.toLowerCase() + " encontrado no sistema.");
            return Collections.emptyList();
        }

        return menuDeFiltrosAdicionais(scanner, petsFiltradosPorTipo);
    }

    // Menu para o usuário determinar o filtro exato para a busca do ept
    private List<Pet> menuDeFiltrosAdicionais(Scanner scanner, List<Pet> petsParaBuscar) {
        int opcao = 0;
        do {
            System.out.print("\n--- Refinar Busca por " + petsParaBuscar.get(0).getTipo() + "s ---\n" +
                    "1. Ver todos os resultados\n" +
                    "2. Buscar por Nome ou Sobrenome\n" +
                    "3. Buscar por Raça\n" +
                    "4. Buscar por Sexo\n" +
                    "5. Buscar por Idade (exata, em anos)\n" +
                    "6. Buscar por Peso (exato, em kg)\n" +
                    "7. Buscar por Endereço (rua, número ou cidade)\n" +
                    "8. Voltar ao Menu Principal\n" +
                    "Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n>> ERRO: Opção inválida! Por favor, digite um número.");
                continue;
            }

            switch (opcao) {
                case 1:
                    return petsParaBuscar;

                case 2: // Nome
                    System.out.print("Digite o nome/sobrenome: ");
                    String nome = scanner.nextLine().trim().toLowerCase();
                    List<Pet> resultadosNome = new ArrayList<>();
                    for (Pet pet : petsParaBuscar) {
                        if (pet.getNomeCompleto().toLowerCase().contains(nome)) {
                            resultadosNome.add(pet);
                        }
                    }
                    return resultadosNome;

                case 3: // Raça
                    System.out.print("Digite a raça: ");
                    String raca = scanner.nextLine().trim().toLowerCase();
                    List<Pet> resultadosRaca = new ArrayList<>();
                    for (Pet pet : petsParaBuscar) {
                        if (pet.getRaca().toLowerCase().contains(raca)) {
                            resultadosRaca.add(pet);
                        }
                    }
                    return resultadosRaca;

                case 4: // Sexo
                    System.out.print("Digite o sexo (Macho/Femea): ");
                    try {
                        SexoPets sexo = SexoPets.valueOf(scanner.nextLine().trim().toUpperCase());
                        List<Pet> resultadosSexo = new ArrayList<>();
                        for (Pet pet : petsParaBuscar) {
                            if (pet.getSexo() == sexo) {
                                resultadosSexo.add(pet);
                            }
                        }
                        return resultadosSexo;
                    } catch (IllegalArgumentException e) {
                        System.out.println(">> ERRO: Sexo inválido.");
                        return null;
                    }

                case 5: // Idade
                    System.out.print("Digite a idade exata em anos (ex: 0.5): ");
                    try {
                        double idade = Double.parseDouble(scanner.nextLine().replace(",", "."));
                        List<Pet> resultadosIdade = new ArrayList<>();
                        for (Pet pet : petsParaBuscar) {
                            if (!pet.getIdadeAproximada().equals(Pet.NAO_INFORMADO)) {
                                double idadePet = Double.parseDouble(pet.getIdadeAproximada().replace(",", "."));
                                if (idadePet == idade) {
                                    resultadosIdade.add(pet);
                                }
                            }
                        }
                        return resultadosIdade;
                    } catch (NumberFormatException e) {
                        System.out.println(">> ERRO: Idade deve ser um número.");
                        return null;
                    }

                case 6: // Peso
                    System.out.print("Digite o peso exato em kg (ex: 4.5): ");
                    try {
                        double peso = Double.parseDouble(scanner.nextLine().replace(",", "."));
                        List<Pet> resultadosPeso = new ArrayList<>();
                        for (Pet pet : petsParaBuscar) {
                            if (!pet.getPesoAproximado().equals(Pet.NAO_INFORMADO)) {
                                double pesoPet = Double.parseDouble(pet.getPesoAproximado().replace(",", "."));
                                if (pesoPet == peso) {
                                    resultadosPeso.add(pet);
                                }
                            }
                        }
                        return resultadosPeso;
                    } catch (NumberFormatException e) {
                        System.out.println(">> ERRO: Peso deve ser um número.");
                        return null;
                    }

                case 7: // Endereço
                    System.out.print("Digite parte do endereço (rua, número ou cidade): ");
                    String endereco = scanner.nextLine().trim().toLowerCase();
                    List<Pet> resultadosEndereco = new ArrayList<>();
                    for (Pet pet : petsParaBuscar) {
                        if (pet.getRua().toLowerCase().contains(endereco) ||
                                pet.getNumeroCasa().toLowerCase().contains(endereco) ||
                                pet.getCidade().toLowerCase().contains(endereco)) {
                            resultadosEndereco.add(pet);
                        }
                    }
                    return resultadosEndereco;

                case 8:
                    System.out.println("Retornando ao menu principal...");
                    return null;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 8);
        return Collections.emptyList();
    }

    // Exibe os pets que foram encontrados
    public void exibirResultados(List<Pet> petsEncontrados) {
        System.out.println("\n--- Resultado da Busca ---");
        if (petsEncontrados.isEmpty()) {
            System.out.println("Nenhum pet encontrado com este(s) critério(s).");
        } else {
            System.out.println(petsEncontrados.size() + " pet(s) encontrado(s):");
            int contador = 1;
            for (Pet pet : petsEncontrados) {
                System.out.println(contador + ". " + pet.toResumoString());
                contador++;
            }
        }
    }

}
