package md.ts14ic.slotsgame.slots;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class SpinLayout {
    private final List<List<Slot>> slots;

    private SpinLayout(@Nonnull List<List<Slot>> slots) {
        this.slots = requireNonNull(slots);
    }

    public static SpinLayout fromGenerator(
            int rowCount,
            int columnCount,
            Generator generator
    ) {
        List<List<Slot>> slots = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < rowCount; ++rowIndex) {
            List<Slot> row = new ArrayList<>();
            for (int columnIndex = 0; columnIndex < columnCount; ++columnIndex) {
                row.add(generator.generate(rowIndex, columnIndex));
            }
            slots.add(Collections.unmodifiableList(row));
        }
        return new SpinLayout(slots);
    }

    public List<List<Slot>> getCells() {
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

    public interface Generator {
        Slot generate(int rowIndex, int columnIndex);
    }
}