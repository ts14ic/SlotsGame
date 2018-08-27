package md.ts14ic.slotsgame.slots.local;

import md.ts14ic.slotsgame.slots.*;

import java.util.List;

public class LocalSetting implements SlotsGame.Setting {
    private final LocalSlotsGenerator localSlotsGenerator = new LocalSlotsGenerator();
    private final LocalSpinLayoutTester localSpinLayoutTester = new LocalSpinLayoutTester();

    @Override
    public SpinResult spin(int betPerLine, int linesBetOnCount) {
        SpinLayout spinLayout = generateSpinLayout();
        List<FoundLine> foundLines = testSpinLayout(spinLayout, betPerLine, linesBetOnCount);
        return new SpinResult(spinLayout, foundLines, betPerLine, linesBetOnCount);
    }

    private SpinLayout generateSpinLayout() {
        return SpinLayout.fromGenerator(getRowCount(), getColumnCount(), this::generateSlot);
    }

    private List<FoundLine> testSpinLayout(SpinLayout result, int betPerLine, int betOnLinesCount) {
        return localSpinLayoutTester.test(result, betPerLine, betOnLinesCount);
    }

    private int getRowCount() {
        return 3;
    }

    private int getColumnCount() {
        return 5;
    }

    private Slot generateSlot(int rowIndex, int columnIndex) {
        return localSlotsGenerator.generate(rowIndex, columnIndex);
    }
}
