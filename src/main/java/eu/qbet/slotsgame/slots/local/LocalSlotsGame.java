package eu.qbet.slotsgame.slots.local;

import eu.qbet.slotsgame.slots.Payline;
import eu.qbet.slotsgame.slots.Slot;
import eu.qbet.slotsgame.slots.SlotSet;
import eu.qbet.slotsgame.slots.SlotsGame;

import java.util.*;

public class LocalSlotsGame implements SlotsGame {
    public static final int ROWS_COUNT = 3;
    public static final int COLUMNS_COUNT = 5;
    private static final Map<Rule, Integer> mPayouts = new HashMap<>();
    private static Rule sDummyRule = new Rule(new Slot(0), 0);

    private List<Payline> mPaylines = new ArrayList<>();
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
        SlotSet slotSet = new SlotSet(ROWS_COUNT, COLUMNS_COUNT);

        for (int r = 0; r < slotSet.getRows(); ++r) {
            for (int c = 0; c < slotSet.getColumns(); ++c) {
                slotSet.setCell(r, c, LocalSlots.getRandomSlot());
            }
        }

        mListener.onGenerated(slotSet);

        testCellset(slotSet, bet, lines);

        mListener.onTestEnd(mPaylines, mTotalPayout);
    }

    private void testCellset(SlotSet set, int bet, int lines) {
        Slot[][] slots = set.getCells();

        lines = Math.max(lines, 1);
        lines = Math.min(lines, LocalPaylines.LINES.length);

        mPaylines.clear();

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
            mPaylines.add(payline);
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