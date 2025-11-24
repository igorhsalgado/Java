package br.com.faesa;

import br.com.faesa.services.MenuService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("      SISTEMA DE CONTROLE DE LEVEL");
        System.out.println("=========================================");
        System.out.println("Grupo 21 - Integrantes:");
        System.out.println("- Igor");
        System.out.println("- Isaque");
        System.out.println("- João Henrique");
        System.out.println("=========================================\n");

        Scanner scanner = new Scanner(System.in);
        MenuService menuService = new MenuService(scanner);
        menuService.iniciar();
        scanner.close();
    }
}
