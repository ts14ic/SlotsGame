package eu.qbet.slotsgame.slots;

import java.util.Arrays;

public class SlotSet {
    private Slot mSlots[][];
    private int mColumns;
    private int mRows;

    public SlotSet() {
        this(0, 0);
    }

    public SlotSet(int rows, int columns) {
        mSlots = new Slot[rows][columns];
        mRows = rows;
        mColumns = columns;
    }

    public void setCell(int row, int column, Slot slot) {
        mSlots[row][column] = slot;
    }

    public void copyFrom(SlotSet otherSet) {
        mSlots = Arrays.copyOf(otherSet.mSlots, otherSet.mSlots.length);
        mColumns = otherSet.mColumns;
        mRows = otherSet.mRows;
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