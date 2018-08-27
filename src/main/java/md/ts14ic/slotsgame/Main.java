package md.ts14ic.slotsgame;

import md.ts14ic.slotsgame.slots.FoundLine;
import md.ts14ic.slotsgame.slots.SlotsGame;
import md.ts14ic.slotsgame.slots.SpinResult;
import md.ts14ic.slotsgame.slots.local.LocalSlotsGame;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SlotsGame slotsGame = new LocalSlotsGame(new SlotsGame.Listener() {
            @Override
            public void onGenerated(SpinResult spinResult) {
                System.out.println(spinResult);
            }

            @Override
            public void onLineFound(FoundLine foundLine) {
                System.out.println("Found line: " + foundLine);
            }

            @Override
            public void onTestEnd(List<FoundLine> foundLines, int totalPayout) {
                System.out.println(foundLines);
                System.out.println("totalPayout: " + totalPayout + "\n");
            }
        });

        slotsGame.spin(100, 30);
    }
}
