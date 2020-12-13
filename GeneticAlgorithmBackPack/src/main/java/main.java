import processing.core.PApplet;
import processing.data.Table;
import java.util.ArrayList;

public class main extends PApplet {
    public static void main(String[] args) {
        PApplet.main("main");
    }

    public static ArrayList<Item> allItemList = new ArrayList<Item>();
    GenerationB gen = new GenerationB(this);
    ProcGraph graph = new ProcGraph(this,10,10,1260,700);
    Table itemInfo;
    float dist;
//!"
    @Override
    public void settings() {
        size(1280,720);
    }

    @Override
    public void setup() {


        makeItem();
        gen.startgen(1500);
        gen.removeBadOnes();

        gen.printOutAllInfo();
        System.out.println("\n\n " +
                "-----------------------------------------------------------------------------------------------------------------------------" +
                "\n\n");

        for (int i=0; i<20000; ++i) {
            gen.nextGeneration();
            int price = gen.getBestPrice();
           // gen.printOutAllInfo();
            GenData data = new GenData(i,price);
            graph.IndputList.add(data);
            System.out.println("Iteration: " + i + ", price: " + price);

            /*for (int k=0; k<gen.backpackList.size(); ++k) {
                System.out.print(gen.backpackList.get(k).calPrize() + " (" + gen.backpackList.get(k).calWeight() +"), ");
            }
            System.out.println();*/

        }

    /*    gen.removeBadOnes();
        gen.getParents();
        gen.parring();
        gen.mutataeAll();*/

    }

    @Override
    public void draw() {
        clear();
        background(200);
        graph.draw();
    }

    @Override
    public void mouseClicked() {
     /*   findTheBestAndMakePArring();
        System.out.println("vægt: "+BorneBassinet.get(1).calWeigth() + " Prices: " + BorneBassinet.get(1).calPrize() + "\n"
                +"vægt: "+BorneBassinet.get(1).calWeigth() + " Prices: " + BorneBassinet.get(1).calPrize() + "\n");
     gen.removeBadOnes();
        gen.getParrents();
        gen.parring();
        gen.mutataeAll();
        dist+=10;

        if(dist>=width-10) {
            clear();
            dist=0;
        }

       rect(dist,height,10, -gen.getBorneBassinet().get(0).calPrize() /10);*/
    }

    void makeItem() {
        itemInfo = loadTable("genstande.csv");

        //Her laves items
        for(int i = 1; i<itemInfo.getRowCount(); ++i) {
            String name = itemInfo.getString(i,0);
            int weight = itemInfo.getInt(i,1);
            int price = itemInfo.getInt(i,2);

            Item item = new Item(name, price, weight);
            allItemList.add(item);
            System.out.println(item.name);
        }
    }

}