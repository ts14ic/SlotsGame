package md.ts14ic.slotsgame.slots;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class RuleLine {
    private final List<Integer> cells;

    public RuleLine(int... cells) {
        this.cells = toList(cells);
    }

    private List<Integer> toList(int[] ints) {
        List<Integer> result = new ArrayList<>(ints.length);
        for (int i : ints) {
            result.add(i);
        }
        return Collections.unmodifiableList(result);
    }

    public int length() {
        return cells.size();
    }

    public int cell(int column) {
        return cells.get(column);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append("|");
        for (int cell : cells) {
            builder.append(cell);
        }
        builder.append("|\n");
        return builder.toString();
    }
}
