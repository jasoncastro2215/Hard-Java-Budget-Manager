package budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean exit = false;
        Budget budget = new Budget();
        while (!exit) {
            System.out.println("Choose your action:\n" +
                    "1) Add income\n" +
                    "2) Add purchase\n" +
                    "3) Show list of purchases\n" +
                    "4) Balance\n" +
                    "5) Save\n" +
                    "6) Load\n" +
                    "7) Analyze (Sort)\n" +
                    "0) Exit");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    System.out.println("\nEnter income:");
                    budget.addIncome(Double.parseDouble(scanner.nextLine()));
                    System.out.println("Income was added!\n");
                    break;
                case "2":
                    while (true) {
                        System.out.println("\nChoose the type of purchase\n" +
                                "1) Food\n" +
                                "2) Clothes\n" +
                                "3) Entertainment\n" +
                                "4) Other\n" +
                                "5) Back");
                        int num = Integer.parseInt(scanner.nextLine());
                        PurchaseType purchaseType = PurchaseType.findByNum(num);
                        if (purchaseType == PurchaseType.ALL) {
                            System.out.println();
                            break;
                        } else
                            chooseTypeToAdd(budget, purchaseType);
                    }
                    break;
                case "3":
                    while (true) {
                        if (budget.getPurchaseList().size() == 0) {
                            System.out.println("\nPurchase list is empty\n");
                            break;
                        }
                        System.out.println("\nChoose the type of purchases\n" +
                                "1) Food\n" +
                                "2) Clothes\n" +
                                "3) Entertainment\n" +
                                "4) Other\n" +
                                "5) All\n" +
                                "6) Back");
                        int num = Integer.parseInt(scanner.nextLine());
                        PurchaseType purchaseType = PurchaseType.findByNum(num);
                        if (purchaseType == null) {
                            System.out.println();
                            break;
                        } else
                            budget.purchaseList(purchaseType);
                    }
                    break;
                case "4":
                    System.out.println(String.format("\nBalance: $%.2f\n", budget.getIncome()));
                    break;
                case "5":
                    save(budget);
                    break;
                case "6":
                    load(budget);
                    break;
                case "7":
                    boolean exit1 = false;
                    while (!exit1) {
                        System.out.println("\nHow do you want to sort?\n" +
                                "1) Sort all purchases\n" +
                                "2) Sort by type\n" +
                                "3) Sort certain type\n" +
                                "4) Back");
                        int num = Integer.parseInt(scanner.nextLine());
                        SortingProcessor sortingProcessor = new SortingProcessor();
                        switch (num) {
                            case 1:
                                sortingProcessor.setMethod(new SortAll());
                                sortingProcessor.sort(budget);
                                break;
                            case 2:
                                sortingProcessor.setMethod(new SortByType());
                                sortingProcessor.sort(budget);
                                break;
                            case 3:
                                System.out.println("\nChoose the type of purchase\n" +
                                        "1) Food\n" +
                                        "2) Clothes\n" +
                                        "3) Entertainment\n" +
                                        "4) Other");
                                PurchaseType purchaseType = PurchaseType.findByNum(
                                        Integer.parseInt(scanner.nextLine()));
                                SortCertainType sortCertainType = new SortCertainType();
                                sortingProcessor.setMethod(sortCertainType);
                                sortCertainType.setPurchaseType(purchaseType);
                                sortingProcessor.sort(budget);
                                break;
                            case 4:
                                System.out.println();
                                exit1 = true;
                        }
                    }
                    break;
                case "0":
                    System.out.println("\nBye!");
                    exit = true;
            }
        }
    }

    static void load(Budget budget) {
        File file = new File("purchases.txt");

        try (Scanner sc = new Scanner(file)) {
            int ctr = 1;
            double income = 0;
            double expense = 0;
            while (sc.hasNextLine()) {
                if (ctr == 1)
                    income = Double.parseDouble(sc.nextLine());
                String[] arr = sc.nextLine().split("~");
                budget.addPurchase(new Purchase(arr[1], Double.parseDouble(arr[2]),
                        PurchaseType.findByNum(Integer.parseInt(arr[0]))));
                expense += Double.parseDouble(arr[2]);
                ctr++;
            }
            budget.addIncome(income + expense);
        } catch (FileNotFoundException ignored) {}
        System.out.println("\nPurchases were loaded!\n");
    }

    static void save(Budget budget) {
        File file = new File("purchases.txt");

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(budget.getIncome() + "\n");
            for (Purchase purchase : budget.getPurchaseList()) {
                writer.write(purchase.getPurchaseType().getNum() + "~" + purchase.getItemName() + "~" +
                        purchase.getPrice() + "\n");
            }
            System.out.println("\nPurchases were saved!\n");
        } catch (IOException e) {
            System.out.printf("\nAn exception occurs %s\n", e.getMessage());
        }
    }

    static void chooseTypeToAdd(Budget budget, PurchaseType purchaseType) {
        System.out.println("\nEnter purchase name:");
        String itemName = scanner.nextLine();
        System.out.println("Enter its price:");
        double price = Double.parseDouble(scanner.nextLine());
        budget.addPurchase(new Purchase(itemName, price, purchaseType));
        System.out.println("Purchase was added!");
    }
}