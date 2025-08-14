import processing.core.PApplet;

public class NumberPadButton extends Button {
    private final String text;

    public NumberPadButton(int xPos, int yPos, int wSize, int hSize, String text) {
        super(xPos, yPos, wSize, hSize);
        this.text = text;
    }

    public String getText(){ return text; }

    public void display(PApplet pa) {
        super.display(pa);
        pa.fill(255);
        pa.stroke(0);
        pa.strokeWeight(2);
        pa.rect(getXPos(), getYPos(), getWSize(), getHSize());
        pa.fill(0);
        pa.textSize(Math.max(16, getHSize() - 20));
        pa.textAlign(PApplet.CENTER, PApplet.CENTER);
        pa.text(text, getXPos(), getYPos());
    }
}
