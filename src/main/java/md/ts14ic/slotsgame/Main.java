package md.ts14ic.slotsgame;

import md.ts14ic.slotsgame.slots.SlotsGame;
import md.ts14ic.slotsgame.slots.SpinResult;

public class Main {
    public static void main(String[] args) {
        SlotsGame slotsGame = new SlotsGame();

        SpinResult spinResult = slotsGame.spin(100, 30);

        System.out.println("Spinned: " + spinResult.getSpinLayout());
        System.out.println("Found lines: " + spinResult.getFoundLines());
        System.out.println(String.format(
                "bet per line: %s\nlines bet on: %s\ntotal bet: %s\ntotal payout: %s\n",
                spinResult.getBetPerLine(),
                spinResult.getLinesBetOnCount(),
                spinResult.getTotalBet(),
                spinResult.getTotalPayout()
        ));
    }
}
