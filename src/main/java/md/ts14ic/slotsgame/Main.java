package md.ts14ic.slotsgame;

import md.ts14ic.slotsgame.slots.Payline;
import md.ts14ic.slotsgame.slots.SlotSet;
import md.ts14ic.slotsgame.slots.SlotsGame;
import md.ts14ic.slotsgame.slots.local.LocalSlotsGame;

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
