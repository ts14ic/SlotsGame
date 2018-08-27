package md.ts14ic.slotsgame;

import md.ts14ic.slotsgame.slots.SlotsGame;
import md.ts14ic.slotsgame.slots.local.LocalSlotsGame;

public class Main {
    public static void main(String[] args) {
        SlotsGame slotsGame = new LocalSlotsGame(
                spinResult -> {
                    System.out.println(spinResult.getSpinLayout());
                    System.out.println(spinResult.getFoundLines());

                    System.out.println(String.format(
                            "bet per line: %s\nlines bet on: %s\ntotal bet: %s\ntotal payout: %s\n",
                            spinResult.getBetPerLine(),
                            spinResult.getLinesBetOnCount(),
                            spinResult.getTotalBet(),
                            spinResult.getTotalPayout()
                    ));
                }
        );

        slotsGame.spin(100, 30);
    }
}
