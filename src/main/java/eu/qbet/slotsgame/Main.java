package eu.qbet.slotsgame;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.execute();
    }

    private void execute() {
        final CellSet cellSet = new CellSet();

        CellSetGenerator cellSetGenerator = new LocalCellSetGenerator(new CellSetGenerator.Listener() {
            @Override
            public void onGenerated(CellSet generatedCellSet) {
                cellSet.copyFrom(generatedCellSet);
                System.out.println(cellSet);
            }

            @Override
            public void onLineFound(Payline payline, int length, int payout) {
                System.out.print(length + " reels, gives: " + payout);
                System.out.print(payline);
            }
        });

        cellSetGenerator.generate();
    }
}
