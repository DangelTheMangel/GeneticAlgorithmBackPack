import processing.core.PApplet;

import processing.data.Table;
import java.util.ArrayList;

public class Generation {
    PApplet p;

    ArrayList<Backpack> BackpackList = new ArrayList<Backpack>();
    public static ArrayList<Backpack> BorneBassinet = new ArrayList<>();
    int bedste1, bedste2;
    Generation(PApplet p){
        this.p = p;
    }

    void startgen(int k){
        //Her laves backpacks
        for(int i = 0; i<k;++i){
            BackpackList.add(new Backpack(p));
            for(int j = 0; j<p.random(1,main.AllItemList.size());++j){
                BackpackList.get(BackpackList.size()-1).addItemToBackpack();
            }



            System.out.println("Samlet pris: " + BackpackList.get(BackpackList.size()-1).calPrize() + " Samlet vægt: " + BackpackList.get(BackpackList.size()-1).calWeigth());
        }
    }
    void removeBadOne(){
        for (int i = 0; i < BackpackList.size(); i++) {
            Backpack b = BackpackList.get(i);
            if(b.calWeigth()>5000){
                BackpackList.remove(i);
            }
        }
    }
    void getParrents(){
        for (int i = 0; i < BackpackList.size(); i++) {
            Backpack b = BackpackList.get(i);
            if(b.calPrize()>bedste1) {
                bedste2=bedste1;
                bedste1=b.calPrize();

                BorneBassinet.add(b);

                if(BorneBassinet.size()==3) {
                    BorneBassinet.remove(2);
                }
            }}
        p.println(bedste1 + " og " + bedste2);
        }

    void mutataeAll(){
        for(int i = 0 ; i<BackpackList.size()-1;++i){
            BackpackList.set(i,mutationSingelOne(BackpackList.get(i))) ;
        }
    }
    Backpack mutationSingelOne(Backpack bp){
        for (int i = 0; i < BackpackList.size(); i++){
            Backpack b = BackpackList.get(i);
            for(int j = 0; j < b.pickedList.size();++j){

                if(p.random(1) < 0.01){
                    b.pickedList.set(j, main.AllItemList.get((int)p.random(0,main.AllItemList.size()-1))) ;
                }
            }
        }

        return bp;
    }
    void parring(){

        //Her bliver de muteret med vores funktion. Kig neden under.
        BackpackList.clear();

        for(int i = 0; i<2000;++i){
            Backpack bp = sex(mutationSingelOne(BorneBassinet.get(0)), mutationSingelOne(BorneBassinet.get(1)));
            BackpackList.add(mutationSingelOne(bp));
        }
    }

    Backpack sex(Backpack best,Backpack nestBest){
        Backpack newGen = new Backpack(p);
        for(int j = 0; j < (best.pickedList.size()-1)/2;++j){
            newGen.pickedList.add(best.pickedList.get(j));
        }

        for(int i = (best.pickedList.size()-1)/2; i < best.pickedList.size()-1;++i){
            newGen.pickedList.add(best.pickedList.get(i));
        }

        return newGen;
    }

    void printOutAllInfo(){
        for(int i = 0 ; i<BackpackList.size()-1;++i){
            Backpack b = BackpackList.get(i);
            int price = b.calPrize();
            int weight = b.calWeigth();
            String consolPrintout = "Backpack: " + i +" Price: " + price + " Weight: " + weight;

            System.out.println(consolPrintout);
        }
    }

    ArrayList<Backpack> getBorneBassinet(){
        return BorneBassinet;
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
