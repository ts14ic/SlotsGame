package md.ts14ic.slotsgame.slots.local;

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

    static Slot getRandomSlot() {
        return SLOTS[RANDOM.nextInt(SLOTS.length)];
    }

    private static final Random RANDOM = new Random();
}
