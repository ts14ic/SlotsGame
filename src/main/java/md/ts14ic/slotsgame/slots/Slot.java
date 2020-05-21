package md.ts14ic.slotsgame.slots;

public class Slot {
    private int type;

    public Slot(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "[" + type + "]";
    }
}