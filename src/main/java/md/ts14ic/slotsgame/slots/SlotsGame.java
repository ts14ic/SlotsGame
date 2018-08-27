package md.ts14ic.slotsgame.slots;

public interface SlotsGame {
    void spin(int betPerLine, int linesBetOnCount);

    interface Listener {
        void onSpinEnd(int betPerLine, int linesBetOnCount, SpinLayout spinLayout, TestResult testResult);
    }

    interface Setting {
        Slot generateSlot(int rowIndex, int columnIndex);

        TestResult testSpinLayout(SpinLayout result, int betPerLine, int betOnLinesCount);

        default SpinLayout generateSpinLayout() {
            return SpinLayout.fromGenerator(getRowCount(), getColumnCount(), this::generateSlot);
        }

        int getRowCount();

        int getColumnCount();
    }
}
