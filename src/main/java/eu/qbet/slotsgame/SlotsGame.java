package eu.qbet.slotsgame;

interface SlotsGame {
    interface Listener {
        void onGenerated(SlotSet generatedSlotSet);
        void onLineFound(Payline payline, int length, int payout);
        void onTestEnd(int totalPayout);
    }

    void spin(int bet, int lines);
}
