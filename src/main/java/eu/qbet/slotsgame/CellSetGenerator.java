package eu.qbet.slotsgame;

interface CellSetGenerator {
    interface Listener {
        void onGenerated(Cellset generatedCellset);
        void onLineFound(Payline payline, int length, int payout);
        void onTestEnd();
    }

    void generate();
}
