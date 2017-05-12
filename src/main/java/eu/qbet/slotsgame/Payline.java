package eu.qbet.slotsgame;

@SuppressWarnings("unused")
public enum Payline {
    LINE01(new int[] {1, 1, 1, 1, 1}),
    LINE02(new int[] {0, 0, 0, 0, 0}),
    LINE03(new int[] {2, 2, 2, 2, 2}),
    LINE04(new int[] {0, 1, 2, 1, 0}),
    LINE05(new int[] {2, 1, 0, 1, 2}),
    LINE06(new int[] {1, 0, 0, 0, 1}),
    LINE07(new int[] {1, 2, 2, 2, 1}),
    LINE08(new int[] {0, 0, 1, 2, 2}),
    LINE09(new int[] {2, 2, 1, 0, 0}),
    LINE10(new int[] {1, 0, 1, 2, 1}),
    LINE11(new int[] {0, 2, 2, 0, 1}),
    LINE12(new int[] {0, 1, 1, 1, 0}),
    LINE13(new int[] {2, 1, 1, 1, 2}),
    LINE14(new int[] {0, 1, 0, 1, 0}),
    LINE15(new int[] {2, 1, 2, 1, 2}),
    LINE16(new int[] {1, 1, 0, 1, 1}),
    LINE17(new int[] {1, 1, 2, 1, 1}),
    LINE18(new int[] {0, 0, 2, 0, 0}),
    LINE19(new int[] {2, 2, 0, 2, 2}),
    LINE20(new int[] {0, 2, 2, 2, 0}),
    LINE21(new int[] {2, 0, 0, 0, 2}),
    LINE22(new int[] {1, 2, 0, 2, 1}),
    LINE23(new int[] {1, 0, 2, 0, 1}),
    LINE24(new int[] {0, 2, 0, 2, 0}),
    LINE25(new int[] {2, 0, 2, 0, 2}),
    LINE26(new int[] {2, 0, 1, 2, 0}),
    LINE27(new int[] {0, 2, 1, 0, 2}),
    LINE28(new int[] {0, 2, 1, 2, 0}),
    LINE29(new int[] {2, 0, 1, 0, 2}),
    LINE30(new int[] {2, 1, 0, 0, 1});

    private int[] mReels;

    Payline(int[] reels) {
        mReels = reels;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for(int i = 0; i < CellSet.ROWS_COUNT; ++i) {
            builder.append("|");
            for (int cell : mReels) {
                builder.append(cell == i ? "*" : " ");
            }
            builder.append("|\n");
        }
        return builder.toString();
    }
}
