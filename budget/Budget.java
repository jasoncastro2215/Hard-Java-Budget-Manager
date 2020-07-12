package budget;

import java.util.ArrayList;

public class Budget {
    private final ArrayList<Purchase> purchaseList;
    private double income;
    {
        purchaseList = new ArrayList<>();
    }

    public void addIncome(double income) {
        this.income += income;
    }
    
    public void addPurchase(Purchase purchase) {
        purchaseList.add(purchase);
        income -= purchase.getPrice();
    }

    public ArrayList<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void purchaseList(PurchaseType purchaseType) {
        double sum = 0;
        System.out.println("\n" + purchaseType.getTypeName() + ":");
        for (Purchase purchase : purchaseList) {
            if (purchaseType.getTypeName().equals("All")){}
            else if (!purchaseType.equals(purchase.getPurchaseType()))
                continue;
            System.out.println(purchase.getItemName() + " $" + String.format("%.2f", purchase.getPrice()));
            sum += purchase.getPrice();
        }
        if (sum != 0)
            System.out.println(String.format("Total sum: $%.2f", sum));
        else
            System.out.println("Purchase list is empty\n");
    }

    public double getIncome() {
        return income;
    }
}
