package md.ts14ic.slotsgame.slots;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class SpinResult {
    private final SpinLayout spinLayout;
    private final List<FoundLine> foundLines;
    private final int betPerLine;
    private final int linesBetOnCount;
    private final int totalBet;
    private final int totalPayout;

    public SpinResult(
            SpinLayout spinLayout,
            List<FoundLine> foundLines,
            int betPerLine,
            int linesBetOnCount
    ) {
        this.spinLayout = requireNonNull(spinLayout);
        this.foundLines = requireNonNull(foundLines);
        this.betPerLine = betPerLine;
        this.linesBetOnCount = linesBetOnCount;
        this.totalBet = betPerLine * linesBetOnCount;
        this.totalPayout = calculateTotalPayout(foundLines);
    }

    private int calculateTotalPayout(List<FoundLine> foundLines) {
        int totalPayout = 0;
        for (FoundLine line : foundLines) {
            totalPayout += line.getReward();
        }
        return totalPayout;
    }

    public SpinLayout getSpinLayout() {
        return spinLayout;
    }

    public List<FoundLine> getFoundLines() {
        return foundLines;
    }

    public int getBetPerLine() {
        return betPerLine;
    }

    public int getLinesBetOnCount() {
        return linesBetOnCount;
    }

    public int getTotalBet() {
        return totalBet;
    }

    public int getTotalPayout() {
        return totalPayout;
    }
}
