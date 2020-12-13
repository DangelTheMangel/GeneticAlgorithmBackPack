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

        for (int i=0; i< 500; ++i) {
            if (backpackList.size() < 2)
                break;

            Backpack bp1 = getAndRemoveBest();
            Backpack bp2 = getAndRemoveBest();

            Backpack bpChild = sex(bp1, bp2);
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
        for (int i = 0; i < backpackList.size(); i++) {
            Backpack b = backpackList.get(i);
            if(b.calWeight()>5000){
                backpackList.remove(i);
            }
        }
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

        for(int i = 0; i<best.itemList.size()/2; ++i) {
            Item item = new Item(best.itemList.get(i));
            child.addItemToBackpack(item);
        }

        for(int i = nextBest.itemList.size()/2; i<nextBest.itemList.size(); ++i) {
            Item item = new Item(nextBest.itemList.get(i));
            child.addItemToBackpack(item);
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

    //
    /*
    void findTheBestAndMakePArring(){
        //Her tages de to bedste backpacks til at gå videre.
        for (int i = 0; i < BackpackList.size(); i++) {
            Backpack b = BackpackList.get(i);

            if(b.calWeigth()>5000){
                BackpackList.remove(i);
            }
            if(b.calPrize()>bedste1) {
                bedste2=bedste1;
                bedste1=b.calPrize();

                BorneBassinet.add(b);

                if(BorneBassinet.size()==3) {
                    BorneBassinet.remove(2);
                }
            }}
        println(bedste1 + " og " + bedste2);


        //Her bliver de muteret med vores funktion. Kig neden under.
        BackpackList.clear();
        Backpack bp = mateing(BorneBassinet.get(0), BorneBassinet.get(1));
        for(int i = 0; i<2000;++i){
            BackpackList.add(mutation(bp));
        }
    }




    //Her laves der en ny backpack
    Backpack mateing(Backpack best,Backpack nestBest){
        Backpack newGen = new Backpack(this);
        for(int j = 0; j < (best.pickedList.size()-1)/2;++j){
            newGen.pickedList.add(best.pickedList.get(j));
        }

        for(int i = (best.pickedList.size()-1)/2; i < best.pickedList.size()-1;++i){
            newGen.pickedList.add(best.pickedList.get(i));
        }

        return newGen;
    }

    //Her er vores muterings funktion
    Backpack mutation(Backpack bp){

        for(int j = 0; j < bp.pickedList.size();++j){
            Item item = bp.pickedList.get(j);
            System.out.println("før mutation " + item.name);
            int ran =(int) random(0,3);
            if(ran == 2){
                int rand = (int) random(0,AllItemList.size());
                item = AllItemList.get(rand);

            }
            System.out.println("efter mutation " + item.name + "\n");
        }
        return bp;
    }
    * */
}
