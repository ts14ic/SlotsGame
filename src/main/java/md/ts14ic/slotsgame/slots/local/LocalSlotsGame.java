package md.ts14ic.slotsgame.slots.local;

import md.ts14ic.slotsgame.slots.Payline;
import md.ts14ic.slotsgame.slots.Slot;
import md.ts14ic.slotsgame.slots.SlotSet;
import md.ts14ic.slotsgame.slots.SlotsGame;

import java.util.*;

public class LocalSlotsGame implements SlotsGame {
    private static final int ROWS_COUNT = 3;
    private static final int COLUMNS_COUNT = 5;
    private static final Map<Rule, Integer> PAYOUTS;

    private List<Payline> mPaylines = new ArrayList<>();
    private SlotsGame.Listener mListener;
    private int mTotalPayout = 0;

    static {
        Map<Rule, Integer> payouts = new HashMap<>();
        payouts.put(new Rule(LocalSlots.SLOTS[0], 2), 40);
        payouts.put(new Rule(LocalSlots.SLOTS[0], 3), 75);
        payouts.put(new Rule(LocalSlots.SLOTS[0], 4), 200);
        payouts.put(new Rule(LocalSlots.SLOTS[0], 5), 750);

        payouts.put(new Rule(LocalSlots.SLOTS[1], 2), 3);
        payouts.put(new Rule(LocalSlots.SLOTS[1], 3), 10);
        payouts.put(new Rule(LocalSlots.SLOTS[1], 4), 30);
        payouts.put(new Rule(LocalSlots.SLOTS[1], 5), 40);

        payouts.put(new Rule(LocalSlots.SLOTS[2], 3), 10);
        payouts.put(new Rule(LocalSlots.SLOTS[2], 4), 10);
        payouts.put(new Rule(LocalSlots.SLOTS[2], 5), 100);

        payouts.put(new Rule(LocalSlots.SLOTS[3], 3), 30);
        payouts.put(new Rule(LocalSlots.SLOTS[3], 4), 100);
        payouts.put(new Rule(LocalSlots.SLOTS[3], 5), 500);
        PAYOUTS = Collections.unmodifiableMap(payouts);
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

        Rule rule = new Rule(startingSlot, length);

        if (PAYOUTS.containsKey(rule)) {
            int payout = PAYOUTS.get(rule) * bet;
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