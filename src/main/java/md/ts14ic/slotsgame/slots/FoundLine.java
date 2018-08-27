package md.ts14ic.slotsgame.slots;

public class FoundLine {
    private final Payline payline;
    private final int length;
    private final int payout;

    public FoundLine(Payline payline, int length, int payout) {
        this.payline = payline;
        this.length = length;
        this.payout = payout;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[ |");
        for (int i = 0; i < payline.length(); ++i) {
            builder.append(payline.cell(i));
        }
        return builder.append("|, length: ")
                .append(length)
                .append(", payout: ")
                .append(payout)
                .append(" ]")
                .toString();
    }
}
