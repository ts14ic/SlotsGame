package eu.qbet.slotsgame;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.execute();
    }

    private void execute() {
        final SlotSet slotSet = new SlotSet();

        SlotsGame slotsGame = new LocalSlotsGame(new SlotsGame.Listener() {
            @Override
            public void onGenerated(SlotSet generatedCellSet) {
                slotSet.copyFrom(generatedCellSet);

                System.out.println(slotSet);
            }

            @Override
            public void onLineFound(Payline payline, int length, int payout) {
                System.out.print(length + " length, gives: " + payout);
                System.out.print(payline);
            }

            @Override
            public void onTestEnd(int totalPayout) {
                System.out.println("totalPayout: " + totalPayout + "\n");
            }
        });

        slotsGame.spin(100, 3);
    }
}
