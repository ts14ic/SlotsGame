package eu.qbet.slotsgame;

interface CellSetGenerator {
    interface Listener { void getCellSet(CellSet cellSet); }

    void generate(CellSetGenerator.Listener listener);
}
