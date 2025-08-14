import processing.core.PApplet;

public class SudoBoard {
    private final int xPos = 100;
    private final int yPos = 100;
    private final int boardSize;
    private final int buttonSize;
    private final SudoButton[][] cells = new SudoButton[9][9];

    public SudoBoard(int boardSize) {
        this.boardSize = boardSize;
        this.buttonSize = boardSize / 15;
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int cx = xPos + buttonSize * c + buttonSize / 2;
                int cy = yPos + buttonSize * r + buttonSize / 2;
                cells[r][c] = new SudoButton(cx, cy, buttonSize, buttonSize, r, c);
            }
        }
    }

    public void syncLocksFromAlgo() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                cells[r][c].setLocked(SudoAlgo.isLocked(r, c));
            }
        }
    }

    public void display(PApplet pa) {
        for (SudoButton[] row : cells) for (SudoButton b : row) b.display(pa);
        drawGridLines(pa);
        drawLegend(pa);
    }

    private void drawGridLines(PApplet pa) {
        pa.pushStyle();
        pa.stroke(0);
        for (int i = 0; i <= 9; i++) {
            int x = xPos + i * buttonSize;
            int y = yPos + i * buttonSize;
            pa.strokeWeight((i % 3 == 0) ? 3 : 1);
            pa.line(xPos, y, xPos + 9 * buttonSize, y);
            pa.line(x, yPos, x, yPos + 9 * buttonSize);
        }
        pa.popStyle();
    }

    private void drawLegend(PApplet pa) {
        pa.fill(255, 0, 0);
        pa.textAlign(PApplet.LEFT, PApplet.TOP);
        pa.textSize(16);
        pa.text("Solve works only when there are no user entries", 675, 500);
    }

    public int[] hitTest(PApplet pa) {
        for (int r = 0; r < 9; r++)
            for (int c = 0; c < 9; c++)
                if (cells[r][c].isOverButton(pa)) return new int[]{r, c};
        return null;
    }

    public void setSelected(int r, int c) {
        clearSelection();
        cells[r][c].setIsSelected(true);
    }

    public void clearSelection() {
        for (SudoButton[] row : cells) for (SudoButton b : row) b.setIsSelected(false);
    }
}
