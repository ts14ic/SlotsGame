package md.ts14ic.slotsgame.local;

import md.ts14ic.slotsgame.slots.*;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class LocalSlotsGame implements SlotsGame {
    private static final int ROW_COUNT = 3;
    private static final int COLUMN_COUNT = 5;
    private final LocalSlotsGenerator slotsGenerator = new LocalSlotsGenerator();
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
        return SpinLayout.fromGenerator(ROW_COUNT, COLUMN_COUNT, this::generateSlot);
    }

    private List<FoundLine> testSpinLayout(SpinLayout result, int betPerLine, int betOnLinesCount) {
        return layoutTester.test(result, betPerLine, betOnLinesCount);
    }

    private Slot generateSlot(int rowIndex, int columnIndex) {
        return slotsGenerator.generate(rowIndex, columnIndex);
    }
}
