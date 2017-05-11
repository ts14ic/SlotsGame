package eu.qbet.slotsgame;

import java.util.Arrays;

class CellSet {
    public static final int ROWS_COUNT = 3;
    public static final int COLUMNS_COUNT = 5;

    private CellType mCells[][] = new CellType[ROWS_COUNT][COLUMNS_COUNT];

    public void setCell(int row, int column, CellType cellType) {
        mCells[row][column] = cellType;
    }

    public void moveFrom(CellSet otherSet) {
        mCells = otherSet.mCells;
        otherSet.mCells = new CellType[ROWS_COUNT][COLUMNS_COUNT];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for (CellType[] row : mCells) {
            builder.append(Arrays.toString(row)).append("\n");
        }
        return builder.toString();
    }
}