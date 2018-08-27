package md.ts14ic.slotsgame.slots.local;

import md.ts14ic.slotsgame.slots.FoundLine;
import md.ts14ic.slotsgame.slots.SlotsGame;
import md.ts14ic.slotsgame.slots.SpinResult;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class LocalSlotsGame implements SlotsGame {
    private static final int ROW_COUNT = 3;
    private static final int COLUMN_COUNT = 5;
    private final LocalSpinResultTester spinResultTester;
    private final SlotsGame.Listener listener;

    public LocalSlotsGame(SlotsGame.Listener listener, LocalSpinResultTester spinResultTester) {
        this.listener = requireNonNull(listener);
        this.spinResultTester = spinResultTester;
    }

    @Override
    public void spin(int betPerLine, int linesBetOnCount) {
        SpinResult spinResult = randomSpinResult();

        listener.onGenerated(spinResult);

        List<FoundLine> foundLines = spinResultTester.test(spinResult, betPerLine, linesBetOnCount);
        for (FoundLine foundLine : foundLines) {
            listener.onLineFound(foundLine);
        }

        listener.onTestEnd(foundLines, getTotalPayout(foundLines));
    }

    private int getTotalPayout(List<FoundLine> foundLines) {
        int totalPayout = 0;
        for (FoundLine line : foundLines) {
            totalPayout += line.getPayout();
        }
        return totalPayout;
    }

    private SpinResult randomSpinResult() {
        return SpinResult.fromGenerator(
                ROW_COUNT,
                COLUMN_COUNT,
                (rowIndex, columnIndex) -> LocalSlots.getRandomSlot()
        );
    }
}