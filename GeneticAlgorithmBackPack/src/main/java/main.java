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
   ArrayList<Backpack> BørneBassinet = new ArrayList<>();
   int bedste1, bedste2;

    @Override
    public void setup() {
        itemInfo = loadTable("genstande.csv");

        for(int i = 0; i<itemInfo.getRowCount();++i){
            AllItemList.add(new Item(itemInfo.getString(i,0),itemInfo.getInt(i,2),itemInfo.getInt(i,1)));
            System.out.println(AllItemList.get(AllItemList.size()-1).name);
        }

        for(int i = 0; i<5000;++i){
            BackpackList.add(new Backpack(this));
            for(int j = 0; j<random(1,AllItemList.size());++j){
                BackpackList.get(BackpackList.size()-1).addItemToBackpack();
            }



            System.out.println("Samlet pris: " + BackpackList.get(BackpackList.size()-1).calPrize() + " Samlet vægt: " + BackpackList.get(BackpackList.size()-1).calWeigth());
        }

        for (int i = 0; i < BackpackList.size(); i++) {
            Backpack b = BackpackList.get(i);

            if(b.calWeigth()>5000){
                BackpackList.remove(i);
            }

            if(b.calPrize()>bedste1) {
                bedste2=bedste1;
                bedste1=b.calPrize();

                BørneBassinet.add(b);

                if(BørneBassinet.size()==3) {
                    BørneBassinet.remove(2);
                }
            }
        }
        println(bedste1 + " og " + bedste2);
        println("2nd: " + BørneBassinet.get(0).calPrize() + " 1st " + BørneBassinet.get(1).calPrize());
    }


    void mutation(ArrayList<Backpack> listen){
        for(int i = 0 ; i< listen.size()-1;++i){
            Backpack bp = listen.get(i);
            for(int j = 0; j < bp.pickedList.size();++j){
                Item item = bp.pickedList.get(j);
                int ran =(int) random(0,1000);
                if(ran == 2){
                    int rand = (int) random(0,AllItemList.size());
                    listen.get(i).pickedList.set(j, AllItemList.get(rand));
                }
            }
        }
    }
}
