package md.ts14ic.slotsgame.local;

import md.ts14ic.slotsgame.slots.Slot;

import java.util.Random;

class LocalSlots {
    static final Slot[] SLOTS = new Slot[]{
            new Slot(0),
            new Slot(1),
            new Slot(2),
            new Slot(3),
            new Slot(4),
            new Slot(5),
            new Slot(6)
    };
    private static final Random RANDOM = new Random();

    private LocalSlots() {
    }

    static Slot createRandomSlot() {
        return SLOTS[RANDOM.nextInt(SLOTS.length)];
    }
}
