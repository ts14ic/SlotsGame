package md.ts14ic.slotsgame.slots;

import java.util.List;

public interface SpinResultTester {
    List<FoundLine> test(SpinResult result, int betPerLine, int betOnLinesCount);
}
