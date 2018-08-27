package md.ts14ic.slotsgame.slots.local;

import md.ts14ic.slotsgame.slots.Slot;
import md.ts14ic.slotsgame.slots.SlotsGame;
import md.ts14ic.slotsgame.slots.SpinResult;

import java.util.Objects;

public class LocalSlotsGame implements SlotsGame {
    private static final int ROW_COUNT = 3;
    private static final int COLUMN_COUNT = 5;

    private SlotsGame.Listener mListener;

    public LocalSlotsGame(SlotsGame.Listener listener) {
        mListener = listener;
    }

    @Override
    public void spin(int bet, int lines) {
        SpinResult spinResult = randomSpinResult();

        mListener.onGenerated(spinResult);

        SpinResultTester tester = new SpinResultTester(spinResult, bet, lines, mListener);

        mListener.onTestEnd(tester.getPaylines(), tester.getTotalPayout());
    }

    private SpinResult randomSpinResult() {
        return SpinResult.fromGenerator(
                ROW_COUNT,
                COLUMN_COUNT,
                (rowIndex, columnIndex) -> LocalSlots.getRandomSlot()
        );
    }

    public static class Rule {
        Slot type;
        int length;

        Rule(Slot type, int length) {
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