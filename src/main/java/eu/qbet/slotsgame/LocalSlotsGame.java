package eu.qbet.slotsgame;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class LocalSlotsGame implements SlotsGame {
    private final static Map<Rule, Integer> mPayouts = new HashMap<>();
    private static Rule sDummyRule = new Rule(Slot.WILD, 0);
    private SlotsGame.Listener mListener;
    private int mTotalPayout = 0;

    static {
        mPayouts.put(new Rule(Slot.WILD, 2), 40);
        mPayouts.put(new Rule(Slot.WILD, 3), 75);
        mPayouts.put(new Rule(Slot.WILD, 4), 200);
        mPayouts.put(new Rule(Slot.WILD, 5), 750);

        mPayouts.put(new Rule(Slot.APPLE, 2), 3);
        mPayouts.put(new Rule(Slot.APPLE, 3), 10);
        mPayouts.put(new Rule(Slot.APPLE, 4), 30);
        mPayouts.put(new Rule(Slot.APPLE, 5), 40);

        mPayouts.put(new Rule(Slot.BANANA, 3), 10);
        mPayouts.put(new Rule(Slot.BANANA, 4), 10);
        mPayouts.put(new Rule(Slot.BANANA, 5), 100);

        mPayouts.put(new Rule(Slot.CHERRY, 3), 30);
        mPayouts.put(new Rule(Slot.CHERRY, 4), 100);
        mPayouts.put(new Rule(Slot.CHERRY, 5), 500);
    }

    public LocalSlotsGame(SlotsGame.Listener listener) {
        mListener = listener;
    }

    @Override
    public void spin(int bet, int lines) {
        mTotalPayout = 0;
        SlotSet slotSet = new SlotSet();

        for (int r = 0; r < SlotSet.ROWS_COUNT; ++r) {
            for (int c = 0; c < SlotSet.COLUMNS_COUNT; ++c) {
                slotSet.setCell(r, c, Slot.randomCell());
            }
        }

        mListener.onGenerated(slotSet);

        testCellset(slotSet, bet, lines);

        mListener.onTestEnd(mTotalPayout);
    }

    private void testCellset(SlotSet set, int bet, int lines) {
        Slot[][] slots = set.getCells();

        lines = Math.max(lines, 1);
        lines = Math.min(lines, Payline.values().length);

        for (int i = 0; i < lines; ++i) {
            testPayline(Payline.values()[i], slots, bet);
        }
    }

    private void testPayline(Payline payline, Slot[][] slots, int bet) {
        Slot startingSlot = slots[payline.cell(0)][0];
        int length = 1;

        for (int i = 1; i < payline.length(); ++i) {
            if (slots[payline.cell(i)][i] == startingSlot) {
                ++length;
            } else {
                break;
            }
        }

        sDummyRule.type = startingSlot;
        sDummyRule.length = length;

        if (mPayouts.containsKey(sDummyRule)) {
            int payout = mPayouts.get(sDummyRule) * bet;
            mTotalPayout += payout;
            mListener.onLineFound(payline, length, payout);
        }
    }

    private static class Rule {
        private Slot type;
        private int length;

        private Rule(Slot type, int length) {
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