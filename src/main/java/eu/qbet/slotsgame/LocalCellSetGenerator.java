package eu.qbet.slotsgame;

class LocalCellSetGenerator implements CellSetGenerator {
    private CellSetGenerator.Listener mListener;

    public LocalCellSetGenerator(CellSetGenerator.Listener listener) {
        mListener = listener;
    }

    @Override
    public void generate() {
        CellSet cellSet = new CellSet();

        for (int r = 0; r < CellSet.ROWS_COUNT; ++r) {
            for (int c = 0; c < CellSet.COLUMNS_COUNT; ++c) {
                cellSet.setCell(r, c, CellType.randomCell());
            }
        }

        mListener.onGenerated(cellSet);

        test(cellSet);
    }

    private void test(CellSet set) {
        mListener.onLineFound(Payline.LINE26, 3, 150);
    }
}