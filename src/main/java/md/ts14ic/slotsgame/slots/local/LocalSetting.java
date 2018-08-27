package md.ts14ic.slotsgame.slots.local;

import md.ts14ic.slotsgame.slots.FoundLine;
import md.ts14ic.slotsgame.slots.Slot;
import md.ts14ic.slotsgame.slots.SlotsGame;
import md.ts14ic.slotsgame.slots.SpinLayout;

import java.util.List;

public class LocalSetting implements SlotsGame.Setting {
    private final LocalSlotsGenerator localSlotsGenerator = new LocalSlotsGenerator();
    private final LocalSpinLayoutTester localSpinLayoutTester = new LocalSpinLayoutTester();

    @Override
    public Slot generateSlot(int rowIndex, int columnIndex) {
        return localSlotsGenerator.generate(rowIndex, columnIndex);
    }

    @Override
    public List<FoundLine> testSpinLayout(SpinLayout result, int betPerLine, int betOnLinesCount) {
        return localSpinLayoutTester.test(result, betPerLine, betOnLinesCount);
    }

    @Override
    public int getRowCount() {
        return 3;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
}
