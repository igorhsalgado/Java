import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double saldo = 2500;
        int menu = 0;
        double valorDeposito = 0;
        double valorTransferencia = 0;

        System.out.println("**************************************************");
        System.out.println("Dados iniciais do cliente: ");
        System.out.println("Nome:              Igor Hermann Salgado");
        System.out.println("Tipo de conta:     Corrente");
        System.out.println("Saldo inicial:     R$"+saldo);
        System.out.println("**************************************************");
        System.out.println("  ");

        while(true){
            System.out.println("  ");
            System.out.println("Operações");
            System.out.println("  ");
            System.out.println("1- Consultar saldos");
            System.out.println("2- Receber valor");
            System.out.println("3- Transferir valor");
            System.out.println("4- Sair");
            System.out.println("  ");
            System.out.println("Digite a opção desejada: ");
            menu = scan.nextInt();

            if (menu == 1){
                System.out.println("O saldo atual é de R$"+saldo);
            } else if (menu == 2) {
                System.out.println("Digite o valor a ser recebido: ");
                valorDeposito = scan.nextDouble();
                saldo = saldo+valorDeposito;
                System.out.println("Após receber o valor, o saldo passa a ser de R$"+saldo);
            } else if (menu == 3) {
                System.out.println("Digite o valor a ser transferiddo: ");
                valorTransferencia = scan.nextDouble();
                if (valorTransferencia > saldo){
                    System.out.println("Não há saldo suficiente para efetuar essa transferência.");
                } else {
                    saldo = saldo-valorTransferencia;
                    System.out.println("O saldo atual, após a transferência é de R$"+saldo);
                }
            } else if (menu == 4) {
                System.out.println("O programa foi encerrado.");
                break;
            } else {
                System.out.println("Digite uma opção válida.");
            }
        }
    }
}