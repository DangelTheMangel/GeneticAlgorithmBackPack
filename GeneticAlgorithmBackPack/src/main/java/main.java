import processing.core.PApplet;
import processing.data.Table;

import java.util.ArrayList;
import java.util.Random;

public class main extends PApplet {
    public static void main(String[] args) {
        PApplet.main("main");
    }
    public static ArrayList<Item> AllItemList = new ArrayList<Item>();
    Generation gen = new Generation(this);
    Table itemInfo;
//!"
    @Override
    public void settings() {
        size(500,500);
    }

    @Override
    public void setup() {
        makeItem();
       gen.startgen(2000);
        gen.getParrents();
        gen.parring();
        gen.printOutAllInfo();


    }

    @Override
    public void draw() {


    }

    @Override
    public void mouseClicked() {
     /*   findTheBestAndMakePArring();
        System.out.println("vægt: "+BorneBassinet.get(1).calWeigth() + " Prices: " + BorneBassinet.get(1).calPrize() + "\n"
                +"vægt: "+BorneBassinet.get(1).calWeigth() + " Prices: " + BorneBassinet.get(1).calPrize() + "\n");

        dist+=10;

        if(dist>=width-10) {
            clear();
            dist=0;
        }

        rect(dist,height,10,-bedste1/10);*/
    }

    void makeItem(){
        itemInfo = loadTable("genstande.csv");

        //Her laves items
        for(int i = 1; i<itemInfo.getRowCount();++i){
            AllItemList.add(new Item(itemInfo.getString(i,0),itemInfo.getInt(i,2),itemInfo.getInt(i,1)));
            System.out.println(AllItemList.get(AllItemList.size()-1).name);
        }
    }



}