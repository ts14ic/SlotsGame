package md.ts14ic.slotsgame;

import md.ts14ic.slotsgame.slots.SlotsGame;
import md.ts14ic.slotsgame.slots.SlotsGameImpl;
import md.ts14ic.slotsgame.slots.local.LocalSlotsGenerator;
import md.ts14ic.slotsgame.slots.local.LocalSpinResultTester;

public class Main {
    public static void main(String[] args) {
        SlotsGame slotsGame = new SlotsGameImpl(
                3,
                5,
                new LocalSlotsGenerator(),
                new LocalSpinResultTester(),
                (spinLayout, testResult) -> {
                    System.out.println(spinLayout);
                    System.out.println(testResult.getFoundLines());
                    System.out.println("totalPayout: " + testResult.getTotalPayout() + "\n");
                }
        );

        slotsGame.spin(100, 30);
    }
}
