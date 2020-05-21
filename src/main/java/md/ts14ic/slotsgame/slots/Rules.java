package md.ts14ic.slotsgame.slots;

import lombok.Getter;
import md.ts14ic.slotsgame.internal.NumberUtils;

import java.util.*;

@Getter
public class Rules {
    private final int width;
    private final int height;
    private final List<Slot> slots;
    private final List<SlotCombo> slotCombos;
    private final List<RuleLine> ruleLines;

    public Rules(int width, int height, List<Slot> slots, List<SlotCombo> slotCombos, List<RuleLine> ruleLines) {
        if (slots == null || slots.isEmpty()) {
            throw new IllegalArgumentException("Must contain at least one slot to generate");
        }
        if (width < 1) {
            throw new IllegalArgumentException("Must have width of at least 1");
        }
        if (height < 1) {
            throw new IllegalArgumentException("Must have height of at least 1");
        }
        this.width = width;
        this.height = height;
        this.slots = slots;
        this.slotCombos = Objects.requireNonNull(slotCombos, "slotCombos");
        this.ruleLines = Objects.requireNonNull(ruleLines, "ruleLines");
    }

    public Slot getSlot(int index) {
        return slots.get(index);
    }

    public int getSlotsCount() {
        return slots.size();
    }

    List<FoundLine> testSpinLayout(SpinLayout result, int betPerLine, int betOnLinesCount) {
        betOnLinesCount = NumberUtils.clamp(betOnLinesCount, 1, ruleLines.size());

        List<FoundLine> foundLines = new ArrayList<>();
        for (int i = 0; i < betOnLinesCount; ++i) {
            testPayline(ruleLines.get(i), result.getSlots(), betPerLine)
                    .map(foundLines::add);
        }
        return Collections.unmodifiableList(foundLines);
    }

    private Optional<FoundLine> testPayline(RuleLine ruleLine, List<List<Slot>> slots, int betPerLine) {
        Slot startingSlot = slots.get(ruleLine.cell(0)).get(0);
        int length = countSlotAlongRuleLine(startingSlot, ruleLine, slots);

        OptionalInt comboReward = getRewardForSlotCombo(startingSlot, length);
        if (comboReward.isPresent()) {
            int comboRewardWithBets = comboReward.getAsInt() * betPerLine;
            return Optional.of(new FoundLine(ruleLine, length, comboRewardWithBets));
        } else {
            return Optional.empty();
        }
    }

    private int countSlotAlongRuleLine(Slot slot, RuleLine line, List<List<Slot>> slots) {
        int length = 1;
        for (int i = 1; i < line.length(); ++i) {
            if (slots.get(line.cell(i)).get(i) == slot) {
                ++length;
            } else {
                // End of line
                break;
            }
        }
        return length;
    }

    private OptionalInt getRewardForSlotCombo(Slot slot, int length) {
        for (SlotCombo slotCombo : slotCombos) {
            if (slotCombo.getSlot().equals(slot) && slotCombo.getLength() == length) {
                return OptionalInt.of(slotCombo.getBetMultiplier());
            }
        }
        return OptionalInt.empty();
    }
}
