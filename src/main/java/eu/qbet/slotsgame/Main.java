package eu.qbet.slotsgame;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.execute();
    }

    private void execute() {
        final Cellset cellset = new Cellset();

        CellSetGenerator cellSetGenerator = new LocalCellSetGenerator(new CellSetGenerator.Listener() {
            private int totalPayout = 0;

            @Override
            public void onGenerated(Cellset generatedCellSet) {
                cellset.copyFrom(generatedCellSet);
                totalPayout = 0;

                System.out.println(cellset);
            }

            @Override
            public void onLineFound(Payline payline, int length, int payout) {
                totalPayout += payout;
                System.out.print(length + " length, gives: " + payout);
                System.out.print(payline);
            }

            @Override
            public void onTestEnd() {
                System.out.println("totalPayout: " + totalPayout + "\n");
            }
        });

        cellSetGenerator.generate();
    }
}
