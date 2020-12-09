import processing.core.PApplet;
import processing.data.Table;

import java.util.ArrayList;
import java.util.Random;

public class main extends PApplet {
    public static void main(String[] args) {
       PApplet.main("main");
    }
    Table itemInfo;
   public static ArrayList<Item> AllItemList = new ArrayList<Item>();
   ArrayList<Backpack> BackpackList = new ArrayList<Backpack>();
   ArrayList<Backpack> BorneBassinet = new ArrayList<>();
   int bedste1, bedste2;


    @Override
    public void setup() {
        itemInfo = loadTable("genstande.csv");

        //Her laves items
        for(int i = 1; i<itemInfo.getRowCount();++i){
            AllItemList.add(new Item(itemInfo.getString(i,0),itemInfo.getInt(i,2),itemInfo.getInt(i,1)));
            System.out.println(AllItemList.get(AllItemList.size()-1).name);
        }

        //Her laves backpacks
        for(int i = 0; i<2000;++i){
            BackpackList.add(new Backpack(this));
            for(int j = 0; j<random(1,AllItemList.size());++j){
                BackpackList.get(BackpackList.size()-1).addItemToBackpack();
            }



            System.out.println("Samlet pris: " + BackpackList.get(BackpackList.size()-1).calPrize() + " Samlet vægt: " + BackpackList.get(BackpackList.size()-1).calWeigth());
        }

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
}
