package budget;

import java.util.ArrayList;

public class SortingProcessor {

    private SortingMethod method;

    // it may contain additional fields as well

    public void setMethod(SortingMethod method) {
        this.method = method;
    }

    public void sort(Budget budget) {
        this.method.sort(budget);
    }

    static boolean isSorted(ArrayList<Purchase> sortedPurchaseList, boolean isSorted) {
        for (int i = 0; i < sortedPurchaseList.size() - 1; i++)
            if (sortedPurchaseList.get(i).getPrice() <
                    sortedPurchaseList.get(i + 1).getPrice()) {
                Purchase temp = sortedPurchaseList.get(i);
                sortedPurchaseList.set(i, sortedPurchaseList.get(i + 1));
                sortedPurchaseList.set(i + 1, temp);
                isSorted = false;
            }
        return isSorted;
    }
}
