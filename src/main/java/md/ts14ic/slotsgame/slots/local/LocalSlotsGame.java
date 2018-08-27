package md.ts14ic.slotsgame.slots.local;

import md.ts14ic.slotsgame.slots.FoundLine;
import md.ts14ic.slotsgame.slots.SlotsGame;
import md.ts14ic.slotsgame.slots.SpinResult;
import md.ts14ic.slotsgame.slots.SpinResultTester;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class LocalSlotsGame implements SlotsGame {
    private final int rowCount;
    private final int columnCount;
    private final SpinResultTester spinResultTester;
    private final SlotsGame.Listener listener;
    private final SpinResult.Generator generator;

    public LocalSlotsGame(
            int rowCount,
            int columnCount,
            SpinResult.Generator generator,
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
        SpinResult spinResult = SpinResult.fromGenerator(rowCount, columnCount, generator);

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
}