package eu.qbet.slotsgame;

interface CellSetGenerator {
    interface Listener {
        void onGenerated(CellSet generatedCellSet);
    }

    void generate(CellSetGenerator.Listener listener);
}
