package budget;

import java.util.*;

public class SortByType implements SortingMethod{
    @Override
    public void sort(Budget budget) {
        PurchaseType[] purchaseTypes = PurchaseType.values();
        ArrayList<Purchase> purchaseList = budget.getPurchaseList();
        ArrayList<TotalByType> totalByTypes = new ArrayList<>();

        for (PurchaseType purchaseType : purchaseTypes) {
            if (purchaseType.getTypeName().equals("All"))
                continue;
            double typeSum = 0;
            for (Purchase purchase : purchaseList) {
                if (purchase.getPurchaseType().equals(purchaseType))
                    typeSum += purchase.getPrice();
            }
            totalByTypes.add(new TotalByType(purchaseType.getTypeName(), typeSum));
        }

        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < totalByTypes.size()-1; i++) {
                if (totalByTypes.get(i).getTotal() <
                        totalByTypes.get(i+1).getTotal()) {
                    TotalByType temp = totalByTypes.get(i);
                    totalByTypes.set(i, totalByTypes.get(i + 1));
                    totalByTypes.set(i + 1, temp);
                    isSorted = false;
                }
            }
        }
        System.out.println("\nTypes:");
        for (TotalByType totalByType : totalByTypes) {
            System.out.println(String.format("%s - $%.2f", totalByType.getType(),
                    totalByType.getTotal()));
        }
    }
}

class TotalByType {
    private final String type;
    private final double total;

    TotalByType(String type, double total) {
        this.type = type;
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public double getTotal() {
        return total;
    }
}
