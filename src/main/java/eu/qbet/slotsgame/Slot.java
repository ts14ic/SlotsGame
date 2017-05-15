package eu.qbet.slotsgame;

public class Slot {
    private int mId;

    public Slot(int id) {
        mId = id;
    }

    @Override
    public String toString() {
        return "[" + mId + "]";
    }
}