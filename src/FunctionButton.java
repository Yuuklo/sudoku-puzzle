import processing.core.PApplet;

public class FunctionButton extends Button {
    private String text;

    public FunctionButton() {}

    public FunctionButton(int xPos, int yPos, int wSize, int hSize, String text) {
        super(xPos, yPos, wSize, hSize);
        this.text = text;
    }

    public String getText(){ return text; }

    public void display(PApplet pa) {
        super.display(pa);
        pa.fill(0);
        pa.textSize(Math.max(14, getHSize() - 40));
        pa.textAlign(PApplet.CENTER, PApplet.CENTER);
        pa.text(text, getXPos(), getYPos());
    }
}
