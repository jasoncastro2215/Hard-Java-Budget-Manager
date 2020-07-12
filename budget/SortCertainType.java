package budget;

import java.util.ArrayList;

public class SortCertainType implements SortingMethod{
    PurchaseType purchaseType;
    @Override
    public void sort(Budget budget) {
        ArrayList<Purchase> purchaceList = budget.getPurchaseList();
        ArrayList<Purchase> sortedPurchaseList = new ArrayList<>();
        double sum = 0;
        for (Purchase purchase : purchaceList) {
            if (purchase.getPurchaseType().equals(purchaseType)) {
                sum += purchase.getPrice();
                sortedPurchaseList.add(purchase);
            }
        }
        if (sortedPurchaseList.size() == 0) {
            System.out.println("\nPurchase list is empty!");
            return;
        }
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = SortingProcessor.isSorted(sortedPurchaseList, true);
        }

        System.out.println("\n" + purchaseType.getTypeName() + ":");
        for (Purchase purchase : sortedPurchaseList) {
            System.out.println(purchase.getItemName() + " $" + String.format("%.2f", purchase.getPrice()));
            sum += purchase.getPrice();
        }

        System.out.println(String.format("Total sum: $%.2f", sum));
    }

    public void setPurchaseType(PurchaseType purchaseType) {
        this.purchaseType = purchaseType;
    }
}
