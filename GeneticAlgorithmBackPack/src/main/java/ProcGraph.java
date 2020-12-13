import processing.core.PApplet;

public class ProcGraph extends Graph {
    ProcGraph(PApplet app, int posX, int posY, int xSize, int ySize) {
        super(app, posX, posY, xSize, ySize);
    }

    @Override
    void draw() {
        int x2;

        for (int i=0; i<IndputList.size(); ++i) {

            int maxList = 0;
            for (int j = 0; j < IndputList.size(); ++j) {
                if (IndputList.get(j).price > maxList) {
                    maxList = IndputList.get(j).price;
                    maxY = Math.max(maxList, maxY);
                }
            }

            xInt = xSize/IndputList.size();
            yInt = ySize*IndputList.get(i).price/maxY;
            x2 = (int) (posX + xInt*i);



            pApplet.rect(x2,pApplet.height-(posY- 120),xInt,-yInt);
        }



    }
    }

