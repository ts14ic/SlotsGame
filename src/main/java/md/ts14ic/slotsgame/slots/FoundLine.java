package md.ts14ic.slotsgame.slots;

public class FoundLine {
    private final RuleLine ruleLine;
    private final int length;
    private final int payout;

    public FoundLine(RuleLine ruleLine, int length, int payout) {
        this.ruleLine = ruleLine;
        this.length = length;
        this.payout = payout;
    }

    int getPayout() {
        return payout;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[ |");
        for (int i = 0; i < ruleLine.length(); ++i) {
            builder.append(ruleLine.cell(i));
        }
        return builder.append("|, length: ")
                .append(length)
                .append(", payout: ")
                .append(payout)
                .append(" ]")
                .toString();
    }
}
