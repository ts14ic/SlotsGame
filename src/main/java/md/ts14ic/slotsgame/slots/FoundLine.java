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

    /**
     * Return a string representation in the following form:
     * <pre>
     *     [ |{01}234|, length: 2, payout: 300 ]
     * </pre>
     * 1. The whole string is enclosed in [] brackets
     * 2. A line enclosed in || brackets is the first element
     * 3. The || line includes a {} line, which is the fragment that brought the payout
     * 4. The length of that fragment is the second element
     * 5. The payout is also listed
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[ |{");
        for (int i = 0; i < ruleLine.length(); ++i) {
            builder.append(ruleLine.cell(i));
            if (i == length - 1) {
                builder.append("}");
            }
        }
        return builder.append("|, length: ")
                .append(length)
                .append(", payout: ")
                .append(payout)
                .append(" ]")
                .toString();
    }
}
