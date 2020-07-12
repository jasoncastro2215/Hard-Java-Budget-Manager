package budget;

import java.util.ArrayList;

public class SortAll implements SortingMethod{
    @Override
    public void sort(Budget budget) {
        ArrayList<Purchase> purchaseList = budget.getPurchaseList();
        if (purchaseList.size() == 0) {
            System.out.println("\nPurchase list is empty!");
            return;
        }
        double sum = 0;
        System.out.println("\nAll:");
        ArrayList<Purchase> sortedPurchaseList;
        sortedPurchaseList = new ArrayList<>(purchaseList);
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = SortingProcessor.isSorted(sortedPurchaseList, true);
        }

        for (Purchase purchase : sortedPurchaseList) {
            System.out.println(purchase.getItemName() + " $" + String.format("%.2f", purchase.getPrice()));
            sum += purchase.getPrice();
        }

        System.out.println(String.format("Total sum: $%.2f", sum));
    }
}
