package md.ts14ic.slotsgame.slots.local;

import md.ts14ic.slotsgame.slots.Payline;
import md.ts14ic.slotsgame.slots.Slot;
import md.ts14ic.slotsgame.slots.SlotsGame;
import md.ts14ic.slotsgame.slots.SpinResult;

import java.util.*;

public class LocalSlotsGame implements SlotsGame {
    private static final int ROWS_COUNT = 3;
    private static final int COLUMNS_COUNT = 5;
    private static final Map<Rule, Integer> PAYOUTS;

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

    private List<Payline> mPaylines = new ArrayList<>();
    private SlotsGame.Listener mListener;
    private int mTotalPayout = 0;

    public LocalSlotsGame(SlotsGame.Listener listener) {
        mListener = listener;
    }

    @Override
    public void spin(int bet, int lines) {
        mTotalPayout = 0;

        SpinResult spinResult = randomSpinResult();
        mListener.onGenerated(spinResult);

        testSpinResult(spinResult, bet, lines);

        mListener.onTestEnd(mPaylines, mTotalPayout);
    }

    private SpinResult randomSpinResult() {
        List<List<Slot>> slots = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < ROWS_COUNT; ++rowIndex) {
            List<Slot> row = new ArrayList<>();
            for (int columnIndex = 0; columnIndex < COLUMNS_COUNT; ++columnIndex) {
                row.add(LocalSlots.getRandomSlot());
            }
            slots.add(row);
        }
        return new SpinResult(slots);
    }

    private void testSpinResult(SpinResult result, int bet, int lines) {
        List<List<Slot>> slots = result.getCells();

        lines = Math.max(lines, 1);
        lines = Math.min(lines, LocalPaylines.LINES.length);

        mPaylines.clear();

        for (int i = 0; i < lines; ++i) {
            testPayline(LocalPaylines.LINES[i], slots, bet);
        }
    }

    private void testPayline(Payline payline, List<List<Slot>> slots, int bet) {
        Slot startingSlot = slots.get(payline.cell(0)).get(0);
        int length = 1;

        for (int i = 1; i < payline.length(); ++i) {
            if (slots.get(payline.cell(i)).get(i) == startingSlot) {
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