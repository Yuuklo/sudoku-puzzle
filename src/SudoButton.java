import processing.core.PApplet;

public class SudoButton extends Button {
    private boolean isSelected = false;
    private boolean isLocked = false;
    private final int rowIndex;
    private final int colIndex;

    public SudoButton(int xPos, int yPos, int wSize, int hSize, int rowIndex, int colIndex) {
        super(xPos, yPos, wSize, hSize);
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public void setIsSelected(boolean s) { this.isSelected = s; }
    public void setLocked(boolean locked) { this.isLocked = locked; }

    public void display(PApplet pa) {
        int x = getXPos(), y = getYPos(), w = getWSize(), h = getHSize();
        int value = SudoAlgo.getValue(rowIndex, colIndex);
        boolean invalid = !isLocked && value != 0 && !SudoAlgo.isSafe(rowIndex, colIndex, value);

        // If invalid, highlight red
        pa.stroke(invalid ? pa.color(200, 0, 0) : pa.color(0));
        pa.strokeWeight(invalid ? 2.5f : 1f);

        // Background state
        pa.noStroke();
        if (isLocked){ 
            pa.fill(235);
        }
        else if (isSelected && invalid){ 
            pa.fill(255, 120, 120, 120);
        }
        else if (isSelected){ 
             pa.fill(255, 255, 0, 120);
        }
        else if (invalid){
            pa.fill(255, 210, 210);
        }
        else if (isOverButton(pa)){
            pa.fill(240);
        }
        else {
            pa.fill(255);
        }

        pa.rect(x, y, w, h);

        if (value != 0) {
            pa.fill(invalid ? pa.color(200, 0, 0) : pa.color(0));
            pa.textAlign(PApplet.CENTER, PApplet.CENTER);
            pa.textSize(32);
            pa.text(value, getXPos(), getYPos());
        }
    }
}
