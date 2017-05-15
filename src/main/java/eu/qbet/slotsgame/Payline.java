package eu.qbet.slotsgame;

@SuppressWarnings("unused")
public class Payline {
    private int[] mReels;

    public Payline(int[] reels) {
        mReels = reels;
    }

    public int length() {
        return mReels.length;
    }

    public int[] cells() {
        return mReels;
    }

    public int cell(int column) {
        return mReels[column];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for (int i = 0; i < SlotSet.ROWS_COUNT; ++i) {
            builder.append("|");
            for (int cell : mReels) {
                builder.append(cell == i ? "*" : " ");
            }
            builder.append("|\n");
        }
        return builder.toString();
    }
}
