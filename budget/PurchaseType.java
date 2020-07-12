package budget;

public enum PurchaseType {
    FOOD(1, "Food"),
    CLOTHES(2, "Clothes"),
    ENTERTAINMENT(3, "Entertainment"),
    OTHER(4, "Other"),
    ALL(5, "All");

    private final int num;
    private final String typeName;

    PurchaseType(int num, String typeName) {
        this.num = num;
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getNum() {
        return num;
    }

    public static PurchaseType findByNum(int num) {
        for (PurchaseType value: values()) {
            if (num == value.num) {
                return value;
            }
        }
        return null;
    }
}