package md.ts14ic.slotsgame.slots;

import lombok.Value;

import java.util.Objects;

@Value
class SlotCombo {
    Slot slot;
    int length;
    int betMultiplier;

    SlotCombo(Slot slot, int length, int betMultiplier) {
        if (length < 1) {
            throw new IllegalArgumentException("Slot combo must be at least 1 cell long, got: " + length);
        }
        this.slot = Objects.requireNonNull(slot, "slot");
        this.length = length;
        this.betMultiplier = betMultiplier; // can even be negative, lol
    }
}
