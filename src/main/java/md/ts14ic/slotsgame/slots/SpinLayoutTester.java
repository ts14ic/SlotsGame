package md.ts14ic.slotsgame.slots;

import java.util.List;

public interface SpinLayoutTester {
    List<FoundLine> test(SpinLayout result, int betPerLine, int betOnLinesCount);
}
