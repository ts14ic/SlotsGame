package eu.qbet.slotsgame;

import java.util.Arrays;

public class SlotSet {
    public static final int ROWS_COUNT = 3;
    public static final int COLUMNS_COUNT = 5;

    private Slot mSlots[][] = new Slot[ROWS_COUNT][COLUMNS_COUNT];

    public void setCell(int row, int column, Slot slot) {
        mSlots[row][column] = slot;
    }

    public void copyFrom(SlotSet otherSet) {
        mSlots = Arrays.copyOf(otherSet.mSlots, otherSet.mSlots.length);
    }

    public Slot[][] getCells() {
        return mSlots;
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