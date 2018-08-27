package md.ts14ic.slotsgame.local;

import md.ts14ic.slotsgame.slots.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class LocalSlotsGame implements SlotsGame {
    private static final int ROW_COUNT = 3;
    private static final int COLUMN_COUNT = 5;
    private final LocalSpinLayoutTester layoutTester = new LocalSpinLayoutTester();
    private final Listener listener;

    public LocalSlotsGame(Listener listener) {
        this.listener = requireNonNull(listener);
    }

    @Override
    public void spin(int betPerLine, int linesBetOnCount) {
        SpinLayout spinLayout = generateSpinLayout();
        List<FoundLine> foundLines = testSpinLayout(spinLayout, betPerLine, linesBetOnCount);

        listener.onSpinEnd(new SpinResult(spinLayout, foundLines, betPerLine, linesBetOnCount));
    }

    private SpinLayout generateSpinLayout() {
        List<List<Slot>> slots = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < ROW_COUNT; ++rowIndex) {
            List<Slot> row = new ArrayList<>();
            for (int columnIndex = 0; columnIndex < COLUMN_COUNT; ++columnIndex) {
                row.add(LocalSlots.createRandomSlot());
            }
            slots.add(Collections.unmodifiableList(row));
        }
        return new SpinLayout(slots);
    }

    private List<FoundLine> testSpinLayout(SpinLayout result, int betPerLine, int betOnLinesCount) {
        return layoutTester.test(result, betPerLine, betOnLinesCount);
    }
}
