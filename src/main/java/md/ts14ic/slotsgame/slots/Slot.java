package md.ts14ic.slotsgame.slots;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Slot {
    private final String name;

    public Slot(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name must not be null, empty or blank");
        }
        this.name = name.trim();
    }

    @Override
    public String toString() {
        return "[" + name.charAt(0) + "]";
    }
}