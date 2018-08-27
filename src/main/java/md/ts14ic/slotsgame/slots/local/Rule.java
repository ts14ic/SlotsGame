package md.ts14ic.slotsgame.slots.local;

import md.ts14ic.slotsgame.slots.Slot;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Rule {
    private final Slot type;
    private final int length;

    Rule(Slot type, int length) {
        this.type = requireNonNull(type);
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return length == rule.length &&
                type == rule.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, length);
    }
}
