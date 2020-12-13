public class Item {
    String name = "";
    int price;
    int weight;
    boolean include;

    Item(String name, int price, int weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    Item(Item item) {
        this.name = item.name;
        this.price = item.price;
        this.weight = item.weight;
        this.include = item.include;
    }

    void setInclude(boolean include) {
        this.include = include;
    }
}
