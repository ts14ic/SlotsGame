package md.ts14ic.slotsgame.slots;

public interface SlotsGame {
    void spin(int betPerLine, int linesBetOnCount);

    interface Listener {
        void onSpinEnd(SpinResult spinResult);
    }
}
