package md.ts14ic.slotsgame.slots;

import java.util.List;

public interface SlotsGame {
    void spin(int betPerLine, int linesBetOnCount);

    interface Listener {
        void onSpinEnd(SpinResult spinResult);
    }

    interface Setting {
        Slot generateSlot(int rowIndex, int columnIndex);

        default SpinResult spin(int betPerLine, int linesBetOnCount) {
            SpinLayout spinLayout = generateSpinLayout();
            List<FoundLine> foundLines = testSpinLayout(spinLayout, betPerLine, linesBetOnCount);
            return new SpinResult(spinLayout, foundLines, betPerLine, linesBetOnCount);
        }

        default SpinLayout generateSpinLayout() {
            return SpinLayout.fromGenerator(getRowCount(), getColumnCount(), this::generateSlot);
        }

        List<FoundLine> testSpinLayout(SpinLayout result, int betPerLine, int betOnLinesCount);

        int getRowCount();

        int getColumnCount();
    }
}
