package eu.qbet.slotsgame;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        CellSetGenerator cellSetGenerator = new LocalCellSetGenerator();

        final CellSet set = new CellSet();
        cellSetGenerator.generate(new CellSetGeneratedListener() {
            @Override
            public void getCellSet(CellSet cellSet) {
                set.moveFrom(cellSet);
            }
        });
    }

    private interface CellSetGeneratedListener {
        void getCellSet(CellSet cellSet);
    }

    private interface CellSetGenerator {
        void generate(CellSetGeneratedListener listener);
    }

    private static class LocalCellSetGenerator implements CellSetGenerator {
        @Override
        public void generate(CellSetGeneratedListener listener) {
            CellSet cellSet = new CellSet();

            for (int r = 0; r < CellSet.ROWS_COUNT; ++r) {
                for (int c = 0; c < CellSet.COLUMNS_COUNT; ++c) {
                    cellSet.setCell(r, c, CellType.randomCell());
                }
            }

            listener.getCellSet(cellSet);
        }
    }

    private static class CellSet {
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
