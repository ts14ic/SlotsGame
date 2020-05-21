package md.ts14ic.slotsgame.slots;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class FairSlotsGame implements SlotsGame {
    private final Rules rules;

    public FairSlotsGame(Rules rules) {
        this.rules = Objects.requireNonNull(rules, "rules");
    }

    @Override
    public SpinResult spin(int betPerLine, int linesBetOnCount) {
        SpinLayout spinLayout = generateSpinLayout();
        List<FoundLine> foundLines = rules.testSpinLayout(spinLayout, betPerLine, linesBetOnCount);

        return new SpinResult(spinLayout, foundLines, betPerLine, linesBetOnCount);
    }

    private SpinLayout generateSpinLayout() {
        List<List<Slot>> slots = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < rules.getHeight(); ++rowIndex) {
            List<Slot> row = new ArrayList<>();
            for (int columnIndex = 0; columnIndex < rules.getWidth(); ++columnIndex) {
                row.add(createRandomSlot());
            }
            slots.add(Collections.unmodifiableList(row));
        }
        return new SpinLayout(slots);
    }

    private Slot createRandomSlot() {
        Random random = ThreadLocalRandom.current();
        return rules.getSlot(random.nextInt(rules.getSlotsCount()));
    }
}
