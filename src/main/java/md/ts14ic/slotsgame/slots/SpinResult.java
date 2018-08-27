package md.ts14ic.slotsgame.slots;

import java.util.Arrays;

public class SpinResult {
    private Slot mSlots[][];
    private int mColumns;
    private int mRows;

    public SpinResult() {
        this(0, 0);
    }

    public SpinResult(int rows, int columns) {
        mSlots = new Slot[rows][columns];
        mRows = rows;
        mColumns = columns;
    }

    public void setCell(int row, int column, Slot slot) {
        mSlots[row][column] = slot;
    }

    public void copyFrom(SpinResult other) {
        mSlots = Arrays.copyOf(other.mSlots, other.mSlots.length);
        mColumns = other.mColumns;
        mRows = other.mRows;
    }

    public Slot[][] getCells() {
        return mSlots;
    }

    public int getColumns() {
        return mColumns;
    }

    public int getRows() {
        return mRows;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for (Slot[] row : mSlots) {
            builder.append(Arrays.toString(row)).append("\n");
        }
        return builder.toString();
    }
}