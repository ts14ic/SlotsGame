package eu.qbet.slotsgame;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        CellSetGenerator cellSetGenerator = new LocalCellSetGenerator();
        CellSet cellSet = cellSetGenerator.generate();

        System.out.println("cellSet = " + cellSet);
    }

    private interface CellSetGenerator {
        CellSet generate();
    }

    private static class LocalCellSetGenerator implements CellSetGenerator {
        @Override
        public CellSet generate() {
            CellSet set = new CellSet();

            for (int r = 0; r < CellSet.ROWS_COUNT; ++r) {
                for (int c = 0; c < CellSet.COLUMNS_COUNT; ++c) {
                    set.setCell(r, c, CellType.randomCell());
                }
            }

            return set;
        }
    }

    private static class CellSet {
        public static final int ROWS_COUNT = 3;
        public static final int COLUMNS_COUNT = 5;

        private CellType mCells[][] = new CellType[ROWS_COUNT][COLUMNS_COUNT];

        public void setCell(int row, int column, CellType cellType) {
            mCells[row][column] = cellType;
        }

        public void setAllCells(CellType[][] cells) {
            mCells = cells;
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

    private enum CellType {
        // TODO
        STUB;

        private static final List<CellType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static CellType randomCell() {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }
}
