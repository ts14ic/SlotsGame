package md.ts14ic.slotsgame.slots.local;

import md.ts14ic.slotsgame.slots.SlotsGame;
import md.ts14ic.slotsgame.slots.SpinResult;

import static java.util.Objects.requireNonNull;

public class LocalSlotsGame implements SlotsGame {
    private static final int ROW_COUNT = 3;
    private static final int COLUMN_COUNT = 5;
    private SlotsGame.Listener mListener;

    public LocalSlotsGame(SlotsGame.Listener listener) {
        mListener = requireNonNull(listener);
    }

    @Override
    public void spin(int bet, int lines) {
        SpinResult spinResult = randomSpinResult();

        mListener.onGenerated(spinResult);

        SpinResultTester tester = new SpinResultTester(spinResult, bet, lines, mListener);

        mListener.onTestEnd(tester.getFoundLines(), tester.getTotalPayout());
    }

    private SpinResult randomSpinResult() {
        return SpinResult.fromGenerator(
                ROW_COUNT,
                COLUMN_COUNT,
                (rowIndex, columnIndex) -> LocalSlots.getRandomSlot()
        );
    }
}