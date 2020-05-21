package md.ts14ic.slotsgame.internal;

public class NumberUtils {
    private NumberUtils() {
        throw new AssertionError("Can't be instantiated");
    }

    public static int clamp(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    public static float clamp(float value, float min, float max) {
        return Math.min(Math.max(value, min), max);
    }
}
