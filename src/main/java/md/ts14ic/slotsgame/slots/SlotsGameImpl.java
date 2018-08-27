package md.ts14ic.slotsgame.slots;

import static java.util.Objects.requireNonNull;

public class SlotsGameImpl implements SlotsGame {
    private final int rowCount;
    private final int columnCount;
    private final SlotsGame.Listener listener;
    private final Setting setting;

    public SlotsGameImpl(
            int rowCount,
            int columnCount,
            Setting setting,
            Listener listener
    ) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.setting = requireNonNull(setting);
        this.listener = requireNonNull(listener);
    }

    @Override
    public void spin(int betPerLine, int linesBetOnCount) {
        SpinLayout spinLayout = SpinLayout.fromGenerator(rowCount, columnCount, setting::generateSlot);
        TestResult testResult = setting.testSpinLayout(spinLayout, betPerLine, linesBetOnCount);

        listener.onSpinEnd(betPerLine, linesBetOnCount, spinLayout, testResult);
    }
}