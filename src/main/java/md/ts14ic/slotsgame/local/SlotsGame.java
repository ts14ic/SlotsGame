package md.ts14ic.slotsgame.local;

import md.ts14ic.slotsgame.slots.FoundLine;
import md.ts14ic.slotsgame.slots.Slot;
import md.ts14ic.slotsgame.slots.SpinLayout;
import md.ts14ic.slotsgame.slots.SpinResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SlotsGame {
    private static final int ROW_COUNT = 3;
    private static final int COLUMN_COUNT = 5;
    private final SpinTester3r5c layoutTester = new SpinTester3r5c();

    public SlotsGame() {
    }

    public SpinResult spin(int betPerLine, int linesBetOnCount) {
        SpinLayout spinLayout = generate3r5cLayout();
        List<FoundLine> foundLines = layoutTester.test(spinLayout, betPerLine, linesBetOnCount);

        return new SpinResult(spinLayout, foundLines, betPerLine, linesBetOnCount);
    }

    private SpinLayout generate3r5cLayout() {
        List<List<Slot>> slots = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < ROW_COUNT; ++rowIndex) {
            List<Slot> row = new ArrayList<>();
            for (int columnIndex = 0; columnIndex < COLUMN_COUNT; ++columnIndex) {
                row.add(Slots.createRandomSlot());
            }
            slots.add(Collections.unmodifiableList(row));
        }
        return new SpinLayout(slots);
    }
}
