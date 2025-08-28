public class Purchase implements Comparable<Purchase>{
    private String description;
    private double price;

    public Purchase(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Purchase: " + description +
                " (R$" + price + ")";
    }

    @Override
    public int compareTo(Purchase anotherPurchase) {
        return Double.valueOf(this.price).compareTo(Double.valueOf(anotherPurchase.price));
    }
}
