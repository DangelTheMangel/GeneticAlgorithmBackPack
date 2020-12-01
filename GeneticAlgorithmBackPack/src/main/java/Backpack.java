import processing.core.PApplet;

import java.util.ArrayList;

public class Backpack {
    PApplet p;
    ArrayList<Item> pickedList = new ArrayList<Item>();
    Backpack(PApplet p){
        this.p = p;
    }

    int calWeigth(){
    int sum = 0;
        for(int i = 0 ; i<pickedList.size();++i){
            sum += pickedList.get(i).Weight;
        }
        return sum;

    }

int calPrize(){
        int sum = 0;
        for(int i = 0 ; i<pickedList.size();++i){
            sum += pickedList.get(i).Price;
        }
        return sum;
}
void addItemToBackpack(){
        pickedList.add(main.AllItemList.get((int) p.random(0,main.AllItemList.size())));
}

}
