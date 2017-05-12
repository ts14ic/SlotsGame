package eu.qbet.slotsgame;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.execute();
    }

    private void execute() {
        CellSetGenerator cellSetGenerator = new LocalCellSetGenerator();

        final CellSet cellSet = new CellSet();
        cellSetGenerator.generate(new CellSetGenerator.Listener() {
            @Override
            public void onGenerated(CellSet generatedCellSet) {
                cellSet.copyFrom(generatedCellSet);
                System.out.println(cellSet);

                // proto
                onLineFound(SlotLine.LINE12, 3);
            }

            // proto
            public void onLineFound(SlotLine slotLine, int length) {
                System.out.println(slotLine);
            }
        });
    }
}
