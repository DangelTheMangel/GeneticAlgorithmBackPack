import processing.core.PApplet;
import processing.data.Table;
import java.util.ArrayList;

public class main extends PApplet {
    public static void main(String[] args) {
        PApplet.main("main");
    }

    public static ArrayList<Item> allItemList = new ArrayList<Item>();
    GenerationB gen = new GenerationB(this);
    ProcGraph graph ;
    Table itemInfo;
    Axis axisX , axisY ;

    AlmindeligKnap addGen;
    TextFlet howManyGen;
//!"
    @Override
    public void settings() {
        size(1280,720);
    }

    @Override
    public void setup() {
        graph = new ProcGraph(this,50,height/2,width-100,height/2);
        axisY = new Axis(this,50,height/2+120,50,50, true,graph.IndputList,graph.xInt,graph.yInt,50000);
        axisX = new Axis(this,50,height/2+120,width-50,height/2+120, false,graph.IndputList,graph.xInt,graph.yInt,5);

        addGen = new AlmindeligKnap(this,50,height-225,300,75,"Add Generations");
        howManyGen = new TextFlet(this,50,height-120,300,75,"Add Generations");
        howManyGen.acceptLetters = false;
        howManyGen.indput = "1";
        makeItem();
        gen.startgen(1500);
        gen.removeBadOnes();

        gen.printOutAllInfo();
        System.out.println("\n\n " +
                "-----------------------------------------------------------------------------------------------------------------------------" +
                "\n\n");

        for (int i=0; i<10; ++i) {
            gen.nextGeneration();
            int price = gen.getBestPrice();
           // gen.printOutAllInfo();
            GenData data = new GenData(graph.IndputList.size() +1,price);
            graph.IndputList.add(data);




        }


    }

    @Override
    public void draw() {
        clear();
        background(200);
        graph.draw();
        axisY.draw();
        axisX.draw();
        addGen.tegnKnap();
        howManyGen.tegnTextFlet();
        GenData dat = graph.IndputList.get(graph.IndputList.size()-1);
        String se = "highest price: " + dat.price + "\nGeneration: " + dat.number;
        text(se ,900,height-225 +12);
        if(addGen.klikket){
           String s = "";
           if(s.equals(howManyGen.indput))
               howManyGen.indput = "0";

            for (int i=0; i<Integer.parseInt(howManyGen.indput); ++i) {
                gen.nextGeneration();
                int price = gen.getBestPrice();
                // gen.printOutAllInfo();
                GenData data = new GenData(graph.IndputList.size() +1,price);
                graph.IndputList.add(data);
                System.out.println("Iteration: " + i + ", price: " + price);



            }
            addGen.registrerRelease();
        }
    }

    @Override
    public void mouseClicked() {
        addGen.registrerKlik(mouseX,mouseY);
        howManyGen.KlikTjek(mouseX,mouseY);

    }

    @Override
    public void keyTyped() {
        howManyGen.keyindput(key);
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