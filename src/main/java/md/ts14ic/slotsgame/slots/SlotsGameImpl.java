package md.ts14ic.slotsgame.slots;

import static java.util.Objects.requireNonNull;

public class SlotsGameImpl implements SlotsGame {
    private final int rowCount;
    private final int columnCount;
    private final SpinResultTester spinResultTester;
    private final SlotsGame.Listener listener;
    private final SpinLayout.Generator generator;

    public SlotsGameImpl(
            int rowCount,
            int columnCount,
            SpinLayout.Generator generator,
            SpinResultTester spinResultTester,
            Listener listener
    ) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.generator = requireNonNull(generator);
        this.spinResultTester = requireNonNull(spinResultTester);
        this.listener = requireNonNull(listener);
    }

    @Override
    public void spin(int betPerLine, int linesBetOnCount) {
        SpinLayout spinLayout = SpinLayout.fromGenerator(rowCount, columnCount, generator);
        TestResult testResult = spinResultTester.test(spinLayout, betPerLine, linesBetOnCount);

        listener.onSpinEnd(spinLayout, testResult);
    }
}