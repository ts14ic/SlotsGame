package md.ts14ic.slotsgame.slots;

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
        builder.append("|");
        for (int cell : mReels) {
            builder.append(cell);
        }
        builder.append("|\n");
        return builder.toString();
    }
}
