package eu.qbet.slotsgame.slots;

import java.util.List;

public interface SlotsGame {
    interface Listener {
        void onGenerated(SlotSet generatedSlotSet);
        void onLineFound(Payline payline, int length, int payout);
        void onTestEnd(List<Payline> paylines, int totalPayout);
    }

    void spin(int bet, int lines);
}
