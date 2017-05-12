package eu.qbet.slotsgame;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class LocalCellSetGenerator implements CellSetGenerator {
    private final static Map<Rule, Integer> mPayouts = new HashMap<>();
    private static Rule sDummyRule = new Rule(Cell.WILD, 0);
    private CellSetGenerator.Listener mListener;

    static {
        mPayouts.put(new Rule(Cell.WILD, 2), 10);
        mPayouts.put(new Rule(Cell.WILD, 3), 30);
        mPayouts.put(new Rule(Cell.WILD, 4), 50);
        mPayouts.put(new Rule(Cell.WILD, 5), 100);

        mPayouts.put(new Rule(Cell.APPLE, 2), 10);
        mPayouts.put(new Rule(Cell.APPLE, 3), 20);
        mPayouts.put(new Rule(Cell.APPLE, 4), 30);
        mPayouts.put(new Rule(Cell.APPLE, 5), 50);

        mPayouts.put(new Rule(Cell.BANANA, 2), 15);
        mPayouts.put(new Rule(Cell.BANANA, 3), 45);
        mPayouts.put(new Rule(Cell.BANANA, 4), 75);
        mPayouts.put(new Rule(Cell.BANANA, 5), 150);

        mPayouts.put(new Rule(Cell.CHERRY, 2), 15);
        mPayouts.put(new Rule(Cell.CHERRY, 3), 45);
        mPayouts.put(new Rule(Cell.CHERRY, 4), 75);
        mPayouts.put(new Rule(Cell.CHERRY, 5), 150);
    }

    public LocalCellSetGenerator(CellSetGenerator.Listener listener) {
        mListener = listener;
    }

    @Override
    public void generate() {
        Cellset cellset = new Cellset();

        for (int r = 0; r < Cellset.ROWS_COUNT; ++r) {
            for (int c = 0; c < Cellset.COLUMNS_COUNT; ++c) {
                cellset.setCell(r, c, Cell.randomCell());
            }
        }

        mListener.onGenerated(cellset);

        testCellset(cellset);

        mListener.onTestEnd();
    }

    private void testCellset(Cellset set) {
        Cell[][] cells = set.getCells();

        for (int i = 0; i < Payline.values().length; ++i) {
            testPayline(Payline.values()[i], cells);
        }
    }

    private void testPayline(Payline payline, Cell[][] cells) {
        Cell startingCell = cells[payline.cell(0)][0];
        int length = 1;

        for (int i = 1; i < payline.length(); ++i) {
            if (cells[payline.cell(i)][i] == startingCell) {
                ++length;
            } else {
                break;
            }
        }

        sDummyRule.type = startingCell;
        sDummyRule.length = length;

        if (mPayouts.containsKey(sDummyRule)) {
            mListener.onLineFound(payline, length, mPayouts.get(sDummyRule));
        }
    }

    private static class Rule {
        private Cell type;
        private int length;

        private Rule(Cell type, int length) {
            this.type = type;
            this.length = length;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Rule rule = (Rule) o;
            return length == rule.length &&
                    type == rule.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, length);
        }
    }
}