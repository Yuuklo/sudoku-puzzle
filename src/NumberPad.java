import processing.core.PApplet;

public class NumberPad {
    private final int size;
    private final int originX;
    private final int originY;
    private final int buttonSize;
    private final NumberPadButton[][] keys = new NumberPadButton[3][3];

    public NumberPad(int size, int originX, int originY) {
        this.size = size;
        this.originX = originX;
        this.originY = originY;
        this.buttonSize = size / 4;

        int counter = 1;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                int x = originX + c * buttonSize + buttonSize / 2;
                int y = originY + r * buttonSize + buttonSize / 2;
                keys[r][c] = new NumberPadButton(x, y, buttonSize - 8, buttonSize - 8, String.valueOf(counter));
                counter++;
            }
        }
    }

    public void display(PApplet pa) {
        pa.fill(0);
        pa.textSize(20);
        pa.textAlign(PApplet.LEFT, PApplet.CENTER);
        pa.text("Enter numbers with keypad", originX, originY - 24);

        for (NumberPadButton[] row : keys) {
            for (NumberPadButton b : row) b.display(pa);
        }
    }

    // Returns digit 1 -> 9 once when a key is clicked, otherwise null
    public Integer handleClick(PApplet pa) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (keys[r][c].isOverButton(pa)) {
                    return Integer.parseInt(keys[r][c].getText());
                }
            }
        }
        return null;
    }
}