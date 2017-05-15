package eu.qbet.slotsgame.local;

import eu.qbet.slotsgame.Payline;
import eu.qbet.slotsgame.Slot;
import eu.qbet.slotsgame.SlotSet;
import eu.qbet.slotsgame.SlotsGame;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LocalSlotsGame implements SlotsGame {
    private final static Map<Rule, Integer> mPayouts = new HashMap<>();
    private static Rule sDummyRule = new Rule(new Slot(0), 0);
    private SlotsGame.Listener mListener;
    private int mTotalPayout = 0;

    static {
        mPayouts.put(new Rule(LocalSlots.SLOTS[0], 2), 40);
        mPayouts.put(new Rule(LocalSlots.SLOTS[0], 3), 75);
        mPayouts.put(new Rule(LocalSlots.SLOTS[0], 4), 200);
        mPayouts.put(new Rule(LocalSlots.SLOTS[0], 5), 750);

        mPayouts.put(new Rule(LocalSlots.SLOTS[1], 2), 3);
        mPayouts.put(new Rule(LocalSlots.SLOTS[1], 3), 10);
        mPayouts.put(new Rule(LocalSlots.SLOTS[1], 4), 30);
        mPayouts.put(new Rule(LocalSlots.SLOTS[1], 5), 40);

        mPayouts.put(new Rule(LocalSlots.SLOTS[2], 3), 10);
        mPayouts.put(new Rule(LocalSlots.SLOTS[2], 4), 10);
        mPayouts.put(new Rule(LocalSlots.SLOTS[2], 5), 100);

        mPayouts.put(new Rule(LocalSlots.SLOTS[3], 3), 30);
        mPayouts.put(new Rule(LocalSlots.SLOTS[3], 4), 100);
        mPayouts.put(new Rule(LocalSlots.SLOTS[3], 5), 500);
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
                slotSet.setCell(r, c, LocalSlots.getRandomSlot());
            }
        }

        mListener.onGenerated(slotSet);

        testCellset(slotSet, bet, lines);

        mListener.onTestEnd(mTotalPayout);
    }

    private void testCellset(SlotSet set, int bet, int lines) {
        Slot[][] slots = set.getCells();

        lines = Math.max(lines, 1);
        lines = Math.min(lines, LocalPaylines.LINES.length);

        for (int i = 0; i < lines; ++i) {
            testPayline(LocalPaylines.LINES[i], slots, bet);
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