package md.ts14ic.slotsgame.slots.local;

import md.ts14ic.slotsgame.slots.Payline;
import md.ts14ic.slotsgame.slots.Slot;
import md.ts14ic.slotsgame.slots.SpinResult;

import java.util.*;

class SpinResultTester {
    private static final Map<Rule, Integer> RULE_TO_PAYOUT = initRuleToPayout();
    private final LocalSlotsGame.Listener listener;
    private final int betPerLine;
    private final int betOnLinesCount;

    private final List<Payline> paylines;
    private int totalPayout;

    SpinResultTester(
            SpinResult spinResult,
            int betPerLine,
            int betOnLinesCount,
            LocalSlotsGame.Listener listener
    ) {
        this.listener = listener;
        this.betPerLine = betPerLine;
        this.betOnLinesCount = clamp(betOnLinesCount, 1, LocalPaylines.LINES.length);
        this.paylines = new ArrayList<>();

        test(spinResult);
    }

    private static int clamp(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    private void test(SpinResult result) {
        paylines.clear();
        for (int i = 0; i < betOnLinesCount; ++i) {
            testPayline(LocalPaylines.LINES[i], result.getCells(), betPerLine);
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

        if (RULE_TO_PAYOUT.containsKey(rule)) {
            int payout = RULE_TO_PAYOUT.get(rule) * bet;
            totalPayout += payout;
            paylines.add(payline);
            listener.onLineFound(payline, length, payout);
        }
    }

    private static Map<Rule, Integer> initRuleToPayout() {
        Map<Rule, Integer> map = new HashMap<>();
        map.put(new Rule(LocalSlots.SLOTS[0], 2), 40);
        map.put(new Rule(LocalSlots.SLOTS[0], 3), 75);
        map.put(new Rule(LocalSlots.SLOTS[0], 4), 200);
        map.put(new Rule(LocalSlots.SLOTS[0], 5), 750);

        map.put(new Rule(LocalSlots.SLOTS[1], 2), 3);
        map.put(new Rule(LocalSlots.SLOTS[1], 3), 10);
        map.put(new Rule(LocalSlots.SLOTS[1], 4), 30);
        map.put(new Rule(LocalSlots.SLOTS[1], 5), 40);

        map.put(new Rule(LocalSlots.SLOTS[2], 3), 10);
        map.put(new Rule(LocalSlots.SLOTS[2], 4), 10);
        map.put(new Rule(LocalSlots.SLOTS[2], 5), 100);

        map.put(new Rule(LocalSlots.SLOTS[3], 3), 30);
        map.put(new Rule(LocalSlots.SLOTS[3], 4), 100);
        map.put(new Rule(LocalSlots.SLOTS[3], 5), 500);
        return Collections.unmodifiableMap(map);
    }

    List<Payline> getPaylines() {
        return Collections.unmodifiableList(paylines);
    }

    int getTotalPayout() {
        return totalPayout;
    }
}
