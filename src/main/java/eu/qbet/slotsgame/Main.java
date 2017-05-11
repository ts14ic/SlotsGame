package eu.qbet.slotsgame;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.execute();
    }

    private void execute() {
        CellSetGenerator cellSetGenerator = new LocalCellSetGenerator();

        final CellSet set = new CellSet();
        cellSetGenerator.generate(new CellSetGenerator.Listener() {
            @Override
            public void getCellSet(CellSet cellSet) {
                set.moveFrom(cellSet);
            }
        });
    }
}
