package md.ts14ic.slotsgame.slots.local;

import md.ts14ic.slotsgame.slots.RuleLine;

class LocalPaylines {
    private LocalPaylines() {}

    static final RuleLine[] LINES = new RuleLine[]{
            new RuleLine(1, 0, 1, 2, 1),
            new RuleLine(1, 1, 1, 1, 1),
            new RuleLine(0, 0, 0, 0, 0),
            new RuleLine(2, 2, 2, 2, 2),
            new RuleLine(0, 1, 2, 1, 0),
            new RuleLine(2, 1, 0, 1, 2),
            new RuleLine(1, 0, 0, 0, 1),
            new RuleLine(1, 2, 2, 2, 1),
            new RuleLine(0, 0, 1, 2, 2),
            new RuleLine(2, 2, 1, 0, 0),
            new RuleLine(0, 2, 2, 0, 1),
            new RuleLine(0, 1, 1, 1, 0),
            new RuleLine(2, 1, 1, 1, 2),
            new RuleLine(0, 1, 0, 1, 0),
            new RuleLine(2, 1, 2, 1, 2),
            new RuleLine(1, 1, 0, 1, 1),
            new RuleLine(1, 1, 2, 1, 1),
            new RuleLine(0, 0, 2, 0, 0),
            new RuleLine(2, 2, 0, 2, 2),
            new RuleLine(0, 2, 2, 2, 0),
            new RuleLine(2, 0, 0, 0, 2),
            new RuleLine(1, 2, 0, 2, 1),
            new RuleLine(1, 0, 2, 0, 1),
            new RuleLine(0, 2, 0, 2, 0),
            new RuleLine(2, 0, 2, 0, 2),
            new RuleLine(2, 0, 1, 2, 0),
            new RuleLine(0, 2, 1, 0, 2),
            new RuleLine(0, 2, 1, 2, 0),
            new RuleLine(2, 0, 1, 0, 2),
            new RuleLine(2, 1, 0, 0, 1)
    };
}
