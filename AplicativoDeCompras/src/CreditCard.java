import java.util.ArrayList;
import java.util.List;

public class CreditCard {
    private double limit;
    private double balance;
    private List<Purchase> purchases;

    public CreditCard(double limit) {
        this.limit = limit;
        this.balance = limit;
        this.purchases = new ArrayList<>();
    }

    public double getLimit() {
        return limit;
    }

    public double getBalance() {
        return balance;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public boolean launchPurchase(Purchase purchase){
        if (this.balance > purchase.getPrice()) {
            this.balance -= purchase.getPrice();
            this.purchases.add(purchase);
            return true;
        }

        return false;
    }

}
