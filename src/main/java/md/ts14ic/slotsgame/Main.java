package md.ts14ic.slotsgame;

import md.ts14ic.slotsgame.slots.*;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        InputStream rulesStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("rules-w5h3.json");
        Rules rules = new RulesParser().parse(rulesStream);

//        doASpin(new FairSlotsGame(rules));
        doASpin(UnfairSlotsGame.builder()
                .rules(rules)
                .currentScore(1000)
                .maxScore(3000)
                .maxChanceToRejectSpin(0.6f)
                .maxSpinsRejected(2)
                .build());
    }

    private static void doASpin(SlotsGame slotsGame) {
        SpinResult spinResult = slotsGame.spin(100, 30);

        System.out.println("Spinned: " + spinResult.getSpinLayout());
        System.out.println("Found lines: " + spinResult.getFoundLines());
        System.out.println(String.format(
                "bet per line: %s\nlines bet on: %s\ntotal bet: %s\ntotal payout: %s\n",
                spinResult.getBetPerLine(),
                spinResult.getLinesBetOnCount(),
                spinResult.getTotalBet(),
                spinResult.getTotalReward()
        ));
    }
}
