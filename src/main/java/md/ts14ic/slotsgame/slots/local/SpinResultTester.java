package md.ts14ic.slotsgame.slots.local;

import md.ts14ic.slotsgame.slots.Payline;
import md.ts14ic.slotsgame.slots.Slot;
import md.ts14ic.slotsgame.slots.SpinResult;

import java.util.*;

class SpinResultTester {
    private final LocalSlotsGame.Listener listener;
    private final List<Payline> paylines;
    private int mTotalPayout;

    private static final Map<LocalSlotsGame.Rule, Integer> PAYOUTS;

    static {
        Map<LocalSlotsGame.Rule, Integer> payouts = new HashMap<>();
        payouts.put(new LocalSlotsGame.Rule(LocalSlots.SLOTS[0], 2), 40);
        payouts.put(new LocalSlotsGame.Rule(LocalSlots.SLOTS[0], 3), 75);
        payouts.put(new LocalSlotsGame.Rule(LocalSlots.SLOTS[0], 4), 200);
        payouts.put(new LocalSlotsGame.Rule(LocalSlots.SLOTS[0], 5), 750);

        payouts.put(new LocalSlotsGame.Rule(LocalSlots.SLOTS[1], 2), 3);
        payouts.put(new LocalSlotsGame.Rule(LocalSlots.SLOTS[1], 3), 10);
        payouts.put(new LocalSlotsGame.Rule(LocalSlots.SLOTS[1], 4), 30);
        payouts.put(new LocalSlotsGame.Rule(LocalSlots.SLOTS[1], 5), 40);

        payouts.put(new LocalSlotsGame.Rule(LocalSlots.SLOTS[2], 3), 10);
        payouts.put(new LocalSlotsGame.Rule(LocalSlots.SLOTS[2], 4), 10);
        payouts.put(new LocalSlotsGame.Rule(LocalSlots.SLOTS[2], 5), 100);

        payouts.put(new LocalSlotsGame.Rule(LocalSlots.SLOTS[3], 3), 30);
        payouts.put(new LocalSlotsGame.Rule(LocalSlots.SLOTS[3], 4), 100);
        payouts.put(new LocalSlotsGame.Rule(LocalSlots.SLOTS[3], 5), 500);
        PAYOUTS = Collections.unmodifiableMap(payouts);
    }

    SpinResultTester(
            SpinResult result,
            int bet,
            int lines,
            LocalSlotsGame.Listener listener
    ) {
        this.listener = listener;
        this.paylines = new ArrayList<>();

        test(result, bet, lines);
    }

    private void test(SpinResult result, int bet, int lines) {
        List<List<Slot>> slots = result.getCells();

        lines = Math.max(lines, 1);
        lines = Math.min(lines, LocalPaylines.LINES.length);

        paylines.clear();

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

        LocalSlotsGame.Rule rule = new LocalSlotsGame.Rule(startingSlot, length);

        if (PAYOUTS.containsKey(rule)) {
            int payout = PAYOUTS.get(rule) * bet;
            mTotalPayout += payout;
            paylines.add(payline);
            listener.onLineFound(payline, length, payout);
        }
    }

    List<Payline> getPaylines() {
        return Collections.unmodifiableList(paylines);
    }

    int getTotalPayout() {
        return mTotalPayout;
    }
}
