import processing.core.PApplet;
import processing.data.Table;

import java.applet.Applet;
import java.util.ArrayList;

public abstract class Graph  {
    PApplet pApplet;

    int xSize, ySize , posX, posY;
    int maxY = Integer.MIN_VALUE;
    float xInt;
    float yInt;
    int colon;
    ArrayList<GenData> IndputList = new ArrayList<GenData>();
    boolean linesOn = true;
    Graph(PApplet app, int posX, int posY, int xSize, int ySize) {
        pApplet = app;


        this.xSize = xSize;
        this.ySize = ySize;
        this.posX = posX;
        this.posY = posY;
        this.IndputList = IndputList;

    }

    void draw(){

    }


    int getRowFromMouse(int mouseX, int mouseY){
        if (mouseX < posX || mouseX > posX + xSize || mouseY < posY || mouseY > posY + ySize)
            return -1;

        return (int) ((mouseX - posX)/xInt);
    }

    float getXInt() {

        return xInt;
    }

    float getYInt() {
        return yInt;
    }

}
