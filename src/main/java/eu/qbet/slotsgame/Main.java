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
                onLineFound(Payline.LINE12, 3);
                onLineFound(Payline.LINE24, 2);
            }

            // proto
            public void onLineFound(Payline payline, int length) {
                System.out.print(length);
                System.out.print(payline);
            }
        });
    }
}
