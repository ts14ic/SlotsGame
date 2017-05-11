package eu.qbet.slotsgame;

public enum SlotLine {
    ONE(new int[][]{{}, {2}, {3}});

    private int[][] mCoords;

    SlotLine(int[][] coords) {
        mCoords = coords;
    }
}
