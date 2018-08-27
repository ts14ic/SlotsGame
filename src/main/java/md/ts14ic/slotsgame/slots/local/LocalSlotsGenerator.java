package md.ts14ic.slotsgame.slots.local;

import md.ts14ic.slotsgame.slots.Slot;
import md.ts14ic.slotsgame.slots.SpinResult;

import java.util.Random;

public class LocalSlotsGenerator implements SpinResult.Generator {
    @Override
    public Slot generate(int rowIndex, int columnIndex) {
        return createRandomSlot();
    }

    private static Slot createRandomSlot() {
        return LocalSlots.SLOTS[RANDOM.nextInt(LocalSlots.SLOTS.length)];
    }

    private static final Random RANDOM = new Random();
}
