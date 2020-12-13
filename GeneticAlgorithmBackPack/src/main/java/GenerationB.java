import org.apache.commons.lang.ObjectUtils;
import processing.core.PApplet;

import java.util.ArrayList;

public class GenerationB {
    PApplet p;

    ArrayList<Backpack> backpackList = new ArrayList<Backpack>();
    public static ArrayList<Backpack> borneBassinet = new ArrayList<>();
    int bedste1, bedste2;

    GenerationB(PApplet p){
        this.p = p;
    }

    void startgen(int numGen){
        //Her laves backpacks
        for (int i = 0; i<numGen; ++i) {
            Backpack backpack = new Backpack(p);
            backpackList.add(backpack);

            for (int j = 0; j < main.allItemList.size(); ++j) {
                Item item = new Item(main.allItemList.get(j));
                item.setInclude(Math.random() > .5);
                backpack.addItemToBackpack(item);
            }

            System.out.println("Samlet pris: " + backpack.calPrize() + " Samlet vægt: " + backpack.calWeight());
        }
    }

    void nextGeneration() {
        ArrayList<Backpack> newGeneration = new ArrayList<Backpack>();

        for (int i=0; i<500; ++i) {
            if (backpackList.size() < 2)
                break;

            Backpack bp1 = getAndRemoveBest();
            Backpack bp2 = getAndRemoveBest();

            Backpack bpChild = sex(bp1, bp2);
            bpChild.mutate();

            newGeneration.add(bp1);
            newGeneration.add(bp2);
            newGeneration.add(bpChild);
        }

        backpackList = newGeneration;
        removeBadOnes();
    }

    int getBestPrice() {
        int bestPrice = -1;
        for (int i=0; i<backpackList.size(); ++i) {
            Backpack backpack = backpackList.get(i);
            bestPrice = Math.max(bestPrice, backpack.calPrize());
        }
        return bestPrice;
    }

    void removeBadOnes() {
        ArrayList<Backpack> keepList = new ArrayList<Backpack>();

        for (int i = 0; i < backpackList.size(); i++) {
            Backpack b = backpackList.get(i);
            if (b.calWeight() <= 5000) {
                keepList.add(backpackList.get(i));
            }
        }

        backpackList = keepList;
    }

    Backpack getAndRemoveBest() {
        int maxPrice = -1;
        int maxIdx = -1;

        for (int i=0; i<backpackList.size(); ++i) {
            Backpack backpack = backpackList.get(i);
            if (backpack.calPrize() > maxPrice){
                maxIdx = i;
                maxPrice = backpack.calPrize();
            }
        }

        if (maxIdx >= 0) {
            Backpack bestBP = backpackList.get(maxIdx);
            backpackList.remove(maxIdx);
            return bestBP;
        }
        return null;
    }

    void getParents() {
        for (int i = 0; i < backpackList.size(); i++) {
            Backpack b = backpackList.get(i);
            if(b.calPrize()>bedste1) {
                bedste2=bedste1;
                bedste1=b.calPrize();

                borneBassinet.add(b);

                if(borneBassinet.size()==3) {
                    borneBassinet.remove(0);
                }
            }}
        p.println(bedste1 + " og " + bedste2);
        }

    Backpack sex(Backpack best, Backpack nextBest) {
        Backpack child = new Backpack(p);

        for(int i = 0; i<best.itemList.size(); ++i) {

            if(Math.random() > .5){
                Item item = new Item(best.itemList.get(i));
                child.addItemToBackpack(item);
            } else {
                Item item = new Item(nextBest.itemList.get(i));
                child.addItemToBackpack(item);
            }

        }

        return child;
    }

    void printOutAllInfo(){
        for(int i = 0; i< backpackList.size()-1; ++i){
            Backpack b = backpackList.get(i);
            int price = b.calPrize();
            int weight = b.calWeight();
            String consolPrintout = "Backpack: " + i +" Price: " + price + " Weight: " + weight;

            System.out.println(consolPrintout);
        }
    }

    ArrayList<Backpack> getBorneBassinet(){
        return borneBassinet;
    }


}
