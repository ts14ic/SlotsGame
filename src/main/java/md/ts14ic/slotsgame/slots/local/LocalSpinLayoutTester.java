package md.ts14ic.slotsgame.slots.local;

import md.ts14ic.slotsgame.slots.*;

import java.util.*;

import static java.util.Objects.requireNonNull;

public class LocalSpinLayoutTester implements SpinLayoutTester {
    private static final List<RuleLine> LINES = initRuleLines();
    private static final Map<Rule, Integer> RULE_TO_PAYOUT = initRuleToPayout();

    private static List<RuleLine> initRuleLines() {
        return Arrays.asList(
                new RuleLine(1, 0, 1, 2, 1),
                new RuleLine(1, 1, 1, 1, 1),
                new RuleLine(0, 0, 0, 0, 0),
                new RuleLine(2, 2, 2, 2, 2),
                new RuleLine(0, 1, 2, 1, 0),
                new RuleLine(2, 1, 0, 1, 2),
                new RuleLine(1, 0, 0, 0, 1),
                new RuleLine(1, 2, 2, 2, 1),
                new RuleLine(0, 0, 1, 2, 2),
                new RuleLine(2, 2, 1, 0, 0),
                new RuleLine(0, 2, 2, 0, 1),
                new RuleLine(0, 1, 1, 1, 0),
                new RuleLine(2, 1, 1, 1, 2),
                new RuleLine(0, 1, 0, 1, 0),
                new RuleLine(2, 1, 2, 1, 2),
                new RuleLine(1, 1, 0, 1, 1),
                new RuleLine(1, 1, 2, 1, 1),
                new RuleLine(0, 0, 2, 0, 0),
                new RuleLine(2, 2, 0, 2, 2),
                new RuleLine(0, 2, 2, 2, 0),
                new RuleLine(2, 0, 0, 0, 2),
                new RuleLine(1, 2, 0, 2, 1),
                new RuleLine(1, 0, 2, 0, 1),
                new RuleLine(0, 2, 0, 2, 0),
                new RuleLine(2, 0, 2, 0, 2),
                new RuleLine(2, 0, 1, 2, 0),
                new RuleLine(0, 2, 1, 0, 2),
                new RuleLine(0, 2, 1, 2, 0),
                new RuleLine(2, 0, 1, 0, 2),
                new RuleLine(2, 1, 0, 0, 1)
        );
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

    @Override
    public TestResult test(SpinLayout result, int betPerLine, int betOnLinesCount) {
        betOnLinesCount = clamp(betOnLinesCount, 1, LINES.size());

        List<FoundLine> foundLines = new ArrayList<>();
        for (int i = 0; i < betOnLinesCount; ++i) {
            testPayline(LINES.get(i), result.getCells(), betPerLine)
                    .map(foundLines::add);
        }
        return new TestResult(foundLines);
    }

    private static int clamp(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    private Optional<FoundLine> testPayline(RuleLine ruleLine, List<List<Slot>> slots, int bet) {
        Slot startingSlot = slots.get(ruleLine.cell(0)).get(0);
        int length = 1;

        for (int i = 1; i < ruleLine.length(); ++i) {
            if (slots.get(ruleLine.cell(i)).get(i) == startingSlot) {
                ++length;
            } else {
                break;
            }
        }

        Rule rule = new Rule(startingSlot, length);

        if (RULE_TO_PAYOUT.containsKey(rule)) {
            int payout = RULE_TO_PAYOUT.get(rule) * bet;
            return Optional.of(new FoundLine(ruleLine, length, payout));
        }
        return Optional.empty();
    }

    private static class Rule {
        private final Slot type;
        private final int length;

        Rule(Slot type, int length) {
            this.type = requireNonNull(type);
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
