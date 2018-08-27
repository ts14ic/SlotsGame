package md.ts14ic.slotsgame.slots;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class SpinLayout {
    private final List<List<Slot>> slots;

    public SpinLayout(@Nonnull List<List<Slot>> slots) {
        this.slots = requireNonNull(slots);
    }

    public List<List<Slot>> getSlots() {
        return Collections.unmodifiableList(slots);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for (List<Slot> row : slots) {
            builder.append(row).append("\n");
        }
        return builder.toString();
    }
}