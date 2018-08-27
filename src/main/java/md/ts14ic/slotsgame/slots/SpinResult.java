package md.ts14ic.slotsgame.slots;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpinResult {
    private List<List<Slot>> mSlots;
    private int mColumns;
    private int mRows;

    public SpinResult() {
        this(0, 0);
    }

    public SpinResult(int rows, int columns) {
        mSlots = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < rows; ++rowIndex) {
            List<Slot> row = new ArrayList<>();
            for (int columnIndex = 0; columnIndex < columns; ++columnIndex) {
                row.add(null);
            }
            mSlots.add(row);
        }
        mRows = rows;
        mColumns = columns;
    }

    public void setCell(int row, int column, Slot slot) {
        mSlots.get(row).set(column, slot);
    }

    public void copyFrom(SpinResult other) {
        mSlots = new ArrayList<>(other.mSlots);
        mColumns = other.mColumns;
        mRows = other.mRows;
    }

    public List<List<Slot>> getCells() {
        return Collections.unmodifiableList(mSlots);
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
        for (List<Slot> row : mSlots) {
            builder.append(row).append("\n");
        }
        return builder.toString();
    }
}