import processing.core.PApplet;

public class Runner extends PApplet {
    private SudoBoard board;
    private NumberPad numPad;
    private FunctionButton solveButton;
    private String status = "";

    // Track player selection
    private int selectedRow = -1;
    private int selectedCol = -1;

    public static void main(String[] args) {
        PApplet.main("Runner", args);
    }

    public void settings() {
        size(1000, 800);
        smooth(4);
    }

    public void setup() {
        rectMode(CENTER);
        textAlign(CENTER, CENTER);
        SudoAlgo.reset();
        SudoAlgo.generatePuzzle(30); // Puzzle generate

        board = new SudoBoard(900);
        numPad = new NumberPad(300, 750, 200);
        solveButton = new FunctionButton(815, 460, 120, 60, "Solve");

        // Locked boxes appear in UI
        board.syncLocksFromAlgo();
    }

    public void draw() {
        background(250);
        board.display(this);
        numPad.display(this);
        solveButton.display(this);

        fill(0);
        textSize(16);
        text(status, 750, 470);
    }

    public void mouseReleased() {
        status = "";

        // Solve button
        if (solveButton.isOverButton(this)) {
            if (SudoAlgo.hasUserEntries()) {
                status = "Clear user entries to use Solve (demo rule).";
                return;
            }
            boolean ok = SudoAlgo.sudoSolve(0, 0);
            status = ok ? "Solved!" : "No solution found.";
            return;
        }

        // Clicking cells
        int[] hit = board.hitTest(this);
        if (hit != null) {
            int r = hit[0], c = hit[1];
            if (!SudoAlgo.isLocked(r, c)) {
                selectedRow = r;
                selectedCol = c;
                board.setSelected(r, c);
            } else {
                selectedRow = selectedCol = -1;
                board.clearSelection();
            }
            return;
        }

        // Clicking on the number pad (only if a cell is selected)
        if (selectedRow != -1 && selectedCol != -1) {
            Integer digit = numPad.handleClick(this); // Returns 1 -> 9 or null
            if (digit != null) {
                SudoAlgo.setValue(selectedRow, selectedCol, digit);
            }
        }
    }
}