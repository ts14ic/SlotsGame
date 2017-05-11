package eu.qbet.slotsgame;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

enum CellType {
    // TODO
    STUB;

    private static final List<CellType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static CellType randomCell() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}