import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Digite o limite do cartao: ");
        double limit = scan.nextDouble();
        scan.nextLine();
        CreditCard card = new CreditCard(limit);

        while (true) {
            System.out.println("Digite a descrição da compra: ");
            String description = scan.nextLine();

            System.out.println("Digite o preço da compra: ");
            String priceString = scan.nextLine();
            double price = Double.parseDouble(priceString);

            Purchase newPurchase = new Purchase(description, price);

            if (card.launchPurchase(newPurchase)) {
                System.out.println("Compra realizada!");
            } else {
                System.out.println("Saldo insuficiente.");
                break;
            }

            System.out.println("Digite 0 para sair, ou digite 1 para continuar.");
            int option = scan.nextInt();
            if (option == 0){
                break;
            }
            scan.nextLine();
        }


        System.out.println("****************************");
        System.out.println("COMPRAS REALIZADAS:\n");

        for (Purchase purchase : card.getPurchases()) {
            System.out.println(purchase.getDescription() + " - " + purchase.getPrice());
        }
        System.out.println("\n****************************");

        System.out.println("\nSaldo atual do cartão: " + card.getBalance());
    }
}