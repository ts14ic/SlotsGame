package md.ts14ic.slotsgame.slots;

import static java.util.Objects.requireNonNull;

public class SlotsGameImpl implements SlotsGame {
    private final SlotsGame.Listener listener;
    private final Setting setting;

    public SlotsGameImpl(Setting setting, Listener listener) {
        this.setting = requireNonNull(setting);
        this.listener = requireNonNull(listener);
    }

    @Override
    public void spin(int betPerLine, int linesBetOnCount) {
        SpinLayout spinLayout = setting.generateSpinLayout();
        TestResult testResult = setting.testSpinLayout(spinLayout, betPerLine, linesBetOnCount);

        listener.onSpinEnd(betPerLine, linesBetOnCount, spinLayout, testResult);
    }
}