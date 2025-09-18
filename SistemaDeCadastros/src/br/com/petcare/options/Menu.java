package br.com.petcare.options;

import java.util.Scanner;

public class Menu {

    // Menu separado para deixar o main mais limpo
    public int exibirEObterOpcao(Scanner scan) {
        System.out.print("\n--- MENU PRINCIPAL ---\n" +
                "1. Realizar cadastro\n" +
                "2. Alterar os dados do pet cadastrado\n" +
                "3. Deletar um pet cadastrado\n" +
                "4. Listar todos os pets cadastrados\n" +
                "5. Listar pets por algum critério (idade, nome, raça)\n" +
                "6. Sair\n" +
                "Escolha uma opção: ");

        while (true) {
            try {
                int opcao = Integer.parseInt(scan.nextLine());
                return opcao;
            } catch (NumberFormatException e) {
                System.out.print("Opção inválida! Por favor, digite um número: ");
            }
        }
    }
}