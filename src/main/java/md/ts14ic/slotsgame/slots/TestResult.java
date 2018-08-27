package md.ts14ic.slotsgame.slots;

import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class TestResult {
    private final List<FoundLine> foundLines;
    private final int totalPayout;

    public TestResult(List<FoundLine> foundLines) {
        this.foundLines = requireNonNull(foundLines);
        this.totalPayout = calculateTotalPayout(foundLines);
    }

    private int calculateTotalPayout(List<FoundLine> foundLines) {
        int totalPayout = 0;
        for (FoundLine line : foundLines) {
            totalPayout += line.getPayout();
        }
        return totalPayout;
    }

    public List<FoundLine> getFoundLines() {
        return Collections.unmodifiableList(foundLines);
    }

    public int getTotalPayout() {
        return totalPayout;
    }
}
