import processing.core.PApplet;

public class Button {
    private int xPos;
    private int yPos;
    private int wSize;
    private int hSize;

    public Button() {}

    public Button(int xPos, int yPos, int wSize, int hSize) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.wSize = wSize;
        this.hSize = hSize;
    }

    public boolean isOverButton(PApplet pa) {
        return pa.mouseX >= xPos - wSize / 2 && pa.mouseX <= xPos + wSize / 2 &&
               pa.mouseY >= yPos - hSize / 2 && pa.mouseY <= yPos + hSize / 2;
    }

    public int getXPos(){ return xPos; }
    public int getYPos(){ return yPos; }
    public int getWSize(){ return wSize; }
    public int getHSize(){ return hSize; }

    public void display(PApplet pa) {
        pa.noStroke();
        if (isOverButton(pa)){ 
            pa.fill(230); // If mouse hovers over button, highlight
        }
        else{ 
            pa.fill(255, 255, 255, 200);
        }
        pa.rect(xPos, yPos, wSize, hSize, 8);
    }
}