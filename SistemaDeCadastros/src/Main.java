import br.com.petcare.classes.Pet;
import br.com.petcare.options.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Menu menu = new Menu();
        GerenciadorDeArquivos gerenciador = new GerenciadorDeArquivos();
        int opcao = 0;
        List<Pet> listaDePets = gerenciador.carregarPets();

        do {
            opcao = menu.exibirEObterOpcao(scan);

            switch (opcao) {
                case 1: // Cadastro
                    RealizadorCadastros realizador = new RealizadorCadastros();
                    Pet petCadastrado = realizador.executar();

                    if (petCadastrado != null) {
                        listaDePets.add(petCadastrado);
                        gerenciador.salvarPet(petCadastrado);

                        System.out.println("\n--- Resumo do cadastro ---");
                        System.out.println(petCadastrado);
                    } else {
                        System.out.println("\nCadastro cancelado ou falhou.");
                    }
                    break;
                case 2: // Alterar cadastro
                    AlteradorPets alterador = new AlteradorPets();
                    alterador.executarAlteracao(scan, listaDePets, gerenciador);
                    break;
                case 3: // Deletar pet
                    DeletadorDePet deletador = new DeletadorDePet();
                    deletador.executarDelecao(scan, listaDePets, gerenciador);
                    break;
                case 4: // Listar todos os pets
                    System.out.println("\n--- Lista de todos os Pets cadastrados ---");
                    if (listaDePets.isEmpty()) {
                        System.out.println("Nenhum pet cadastrado no sistema.");
                    } else {
                        for (Pet pet : listaDePets) {
                            System.out.println(pet);
                        }
                    }
                    break;
                case 5: // Listar com critérios
                    BuscadorDePets buscador = new BuscadorDePets();
                    buscador.executarBusca(scan, listaDePets);
                    break;
                case 6: // Sair
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opcao != 6);

        scan.close();
    }
}