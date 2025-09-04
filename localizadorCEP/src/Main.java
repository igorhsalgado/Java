import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CepQuery cepQuery = new CepQuery();

        int option = -1;

        while (option != 2) {
            System.out.println("---------------------------------");
            System.out.println("    MENU DE BUSCA DE ENDEREÇO");
            System.out.println("---------------------------------");
            System.out.println("1. Buscar um novo CEP");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");

            option = scan.nextInt();
            scan.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Digite um número de CEP: ");
                    var cep = scan.nextLine();

                    try {
                        Adress newAdress = cepQuery.searchAdress(cep);
                        System.out.println("\nEndereço encontrado:\n");
                        System.out.println(newAdress);

                        FileGenerator generator = new FileGenerator();
                        generator.generateJson(newAdress);

                    } catch (RuntimeException | IOException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 2:
                    System.out.println("Finalizando a aplicação!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
        scan.close();
    }
}