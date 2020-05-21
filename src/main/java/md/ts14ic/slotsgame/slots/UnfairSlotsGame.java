package md.ts14ic.slotsgame.slots;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import md.ts14ic.slotsgame.internal.NumberUtils;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Builder
@Slf4j
public class UnfairSlotsGame implements SlotsGame {
    private final Rules rules;
    private final int maxScore;
    private final float maxChanceToRejectSpin;
    private final int maxSpinsRejected;
    private int currentScore;

    public UnfairSlotsGame(Rules rules, int maxScore, float maxChanceToRejectSpin, int maxSpinsRejected, int currentScore) {
        if (maxScore < 0) {
            throw new IllegalArgumentException("maxScore must be at least 0, got: " + maxScore);
        }
        if (maxChanceToRejectSpin < 0.0f || maxChanceToRejectSpin > 1.0f) {
            throw new IllegalArgumentException("maxChanceToRejectSpin must be between 0 and 1, got: " + maxChanceToRejectSpin);
        }
        if (maxSpinsRejected < 0) {
            throw new IllegalArgumentException("maxSpinsRejected must be at least 0, got: " + maxScore);
        }
        this.rules = Objects.requireNonNull(rules, "rules");
        this.maxScore = maxScore;
        this.maxChanceToRejectSpin = maxChanceToRejectSpin;
        this.maxSpinsRejected = maxSpinsRejected;
        this.currentScore = currentScore;
    }

    @Override
    public SpinResult spin(int betPerLine, int linesBetOnCount) {
        // do at least one spin
        // if the spin failed - we're fine, just return it
        // if the spin succeeded
        //    if number of spins rejected reached max - well, accept it
        //    if number of spins rejected is still low - throw dice
        //        if dice is high enough - accept the spin
        //        else reject the spin

        List<SpinResult> rejectedSpins = new ArrayList<>();
        // Do at least one spin
        while (true) {
            SpinLayout spinLayout = generateSpinLayout();
            List<FoundLine> foundLines = rules.testSpinLayout(spinLayout, betPerLine, linesBetOnCount);
            SpinResult spinResult = new SpinResult(spinLayout, foundLines, betPerLine, linesBetOnCount);

            if (spinResult.getTotalReward() <= 0) {
                // The spin failed, we're fine.
                log.trace("Spin failed naturally");
                return spinResult;
            } else if (rejectedSpins.size() >= maxSpinsRejected) {
                // We rejected too many spins (this may never end) - just return the least rewarding one.
                if (rejectedSpins.isEmpty()) {
                    log.trace("Spins not allowed to be rejected, returning current");
                    return spinResult;
                } else {
                    log.trace("Too many spins rejected, returning least rewarding");
                    return rejectedSpins.stream()
                            .min(Comparator.comparing(SpinResult::getTotalReward))
                            .get();
                }
            } else {
                float dice = ThreadLocalRandom.current().nextFloat();
                if (dice > getChanceToRejectSpin()) {
                    log.trace("The user won, returning his rewarding spin" +
                            " with dice: {} vs {}", dice, getChanceToRejectSpin());
                    currentScore += spinResult.getTotalReward();
                    return spinResult;
                } else {
                    log.trace("Spin rejected: " + spinResult.getTotalReward() +
                            " with dice: {} vs {}", dice, getChanceToRejectSpin());
                    rejectedSpins.add(spinResult);
                }
            }
        }
    }

    private float getChanceToRejectSpin() {
        if (maxScore > 0) {
            // The close user is to max score, the higher the chance to reject a winning spin
            float scoreRatio = (float) currentScore / maxScore;
            return NumberUtils.clamp(scoreRatio, 0.0f, maxChanceToRejectSpin);
        } else {
            return maxChanceToRejectSpin;
        }
    }

    private SpinLayout generateSpinLayout() {
        List<List<Slot>> slots = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < rules.getHeight(); ++rowIndex) {
            List<Slot> row = new ArrayList<>();
            for (int columnIndex = 0; columnIndex < rules.getWidth(); ++columnIndex) {
                row.add(createRandomSlot());
            }
            slots.add(Collections.unmodifiableList(row));
        }
        return new SpinLayout(slots);
    }

    private Slot createRandomSlot() {
        Random random = ThreadLocalRandom.current();
        return rules.getSlot(random.nextInt(rules.getSlotsCount()));
    }
}
