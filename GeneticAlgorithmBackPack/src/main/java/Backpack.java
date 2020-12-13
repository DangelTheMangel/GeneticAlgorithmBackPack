import processing.core.PApplet;

import java.util.ArrayList;

public class Backpack {
    PApplet p;
    ArrayList<Item> itemList = new ArrayList<Item>();
    int weight = -1;
    int price = -1;

    Backpack(PApplet p){
        this.p = p;
    }

    int calWeight() {
        if (weight > 0)
            return weight;

        weight = 0;
        for (int i = 0; i< itemList.size(); ++i) {
            Item item = itemList.get(i);
            if (item.include) {
                weight += item.weight;
            }
        }
        return weight;
    }

    int calPrize() {
        if (price > 0)
            return price;

        price = 0;
        for (int i = 0; i< itemList.size(); ++i) {
            Item item = itemList.get(i);
            if (item.include) {
                price += item.price;
            }
        }
        return price;
    }

    void addItemToBackpack(Item item) {
            itemList.add(item);
    }

    void mutate() {
        for(int j = 0; j < itemList.size(); ++j) {
            Item item = itemList.get(j);
            if(p.random(1) < 0.5){
                item.setInclude(!item.include);
            }
        }


    }
}
