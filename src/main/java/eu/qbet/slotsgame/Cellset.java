package eu.qbet.slotsgame;

import java.util.Arrays;

class Cellset {
    public static final int ROWS_COUNT = 3;
    public static final int COLUMNS_COUNT = 5;

    private Cell mCells[][] = new Cell[ROWS_COUNT][COLUMNS_COUNT];

    public void setCell(int row, int column, Cell cell) {
        mCells[row][column] = cell;
    }

    public void copyFrom(Cellset otherSet) {
        mCells = Arrays.copyOf(otherSet.mCells, otherSet.mCells.length);
    }

    public Cell[][] getCells() {
        return mCells;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for (Cell[] row : mCells) {
            builder.append(Arrays.toString(row)).append("\n");
        }
        return builder.toString();
    }
}