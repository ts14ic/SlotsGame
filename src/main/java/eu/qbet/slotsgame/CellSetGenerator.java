package eu.qbet.slotsgame;

interface CellSetGenerator {
    interface Listener {
        void onGenerated(CellSet generatedCellSet);
        void onLineFound(Payline payline, int length, int payout);
    }

    void generate();
}
