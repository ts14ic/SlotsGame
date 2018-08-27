package md.ts14ic.slotsgame.slots;

import static java.util.Objects.requireNonNull;

public class SlotsGameImpl implements SlotsGame {
    private final int rowCount;
    private final int columnCount;
    private final SpinLayoutTester spinLayoutTester;
    private final SlotsGame.Listener listener;
    private final SpinLayout.Generator generator;

    public SlotsGameImpl(
            int rowCount,
            int columnCount,
            SpinLayout.Generator generator,
            SpinLayoutTester spinLayoutTester,
            Listener listener
    ) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.generator = requireNonNull(generator);
        this.spinLayoutTester = requireNonNull(spinLayoutTester);
        this.listener = requireNonNull(listener);
    }

    @Override
    public void spin(int betPerLine, int linesBetOnCount) {
        SpinLayout spinLayout = SpinLayout.fromGenerator(rowCount, columnCount, generator);
        TestResult testResult = spinLayoutTester.test(spinLayout, betPerLine, linesBetOnCount);

        listener.onSpinEnd(betPerLine, linesBetOnCount, spinLayout, testResult);
    }
}