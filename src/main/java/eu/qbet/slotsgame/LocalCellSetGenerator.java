package eu.qbet.slotsgame;

class LocalCellSetGenerator implements CellSetGenerator {
    @Override
    public void generate(CellSetGenerator.Listener listener) {
        CellSet cellSet = new CellSet();

        for (int r = 0; r < CellSet.ROWS_COUNT; ++r) {
            for (int c = 0; c < CellSet.COLUMNS_COUNT; ++c) {
                cellSet.setCell(r, c, CellType.randomCell());
            }
        }

        listener.getCellSet(cellSet);
    }
}