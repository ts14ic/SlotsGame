package md.ts14ic.slotsgame;

import md.ts14ic.slotsgame.slots.SlotsGame;
import md.ts14ic.slotsgame.slots.SlotsGameImpl;
import md.ts14ic.slotsgame.slots.local.LocalSetting;

public class Main {
    public static void main(String[] args) {
        SlotsGame slotsGame = new SlotsGameImpl(
                3,
                5,
                new LocalSetting(),
                (betPerLine, linesBetOnCount, spinLayout, testResult) -> {
                    System.out.println(spinLayout);
                    System.out.println(testResult.getFoundLines());

                    System.out.println(String.format(
                            "bet per line: %s\nlines bet on: %s\ntotal bet: %s\ntotal payout: %s\n",
                            betPerLine, linesBetOnCount, betPerLine * linesBetOnCount, testResult.getTotalPayout()
                    ));
                }
        );

        slotsGame.spin(100, 30);
    }
}
