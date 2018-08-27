package md.ts14ic.slotsgame.slots;

import java.util.List;

public interface SlotsGame {
    interface Listener {
        void onGenerated(SpinResult generatedSpinResult);
        void onLineFound(FoundLine foundLine);
        void onTestEnd(List<FoundLine> foundLines, int totalPayout);
    }

    void spin(int bet, int lines);
}
