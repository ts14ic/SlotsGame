package eu.qbet.slotsgame;

import eu.qbet.slotsgame.slots.Payline;
import eu.qbet.slotsgame.slots.SlotSet;
import eu.qbet.slotsgame.slots.SlotsGame;
import eu.qbet.slotsgame.slots.local.LocalSlotsGame;

import java.util.List;

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
            public void onLineFound(Payline payline, int length, int payout) {}

            @Override
            public void onTestEnd(List<Payline> paylines, int totalPayout) {
                System.out.println(paylines);
                System.out.println("totalPayout: " + totalPayout + "\n");
            }
        });

        slotsGame.spin(100, 30);
    }
}
