package budget;

public class Purchase {
    private final String itemName;
    private final double price;
    private final PurchaseType purchaseType;

    Purchase(String itemName, double price, PurchaseType purchaseType) {
        this.itemName = itemName;
        this.price = price;
        this.purchaseType = purchaseType;
    }

    public double getPrice() {
        return price;
    }

    public String getItemName() {
        return itemName;
    }

    public PurchaseType getPurchaseType() {
        return purchaseType;
    }
}
