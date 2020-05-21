package md.ts14ic.slotsgame.slots;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RulesParser {
    private final ObjectReader jsonParser;

    public RulesParser() {
        this.jsonParser = new ObjectMapper().readerFor(RulesJson.class);
    }

    public Rules parse(InputStream rulesJsonInputStream) throws IOException {
        RulesJson rulesJson = jsonParser.readValue(rulesJsonInputStream);
        return fromJson(rulesJson);
    }

    public Rules parse(String rulesJsonString) throws JsonProcessingException {
        RulesJson rulesJson = jsonParser.readValue(rulesJsonString);
        return fromJson(rulesJson);
    }

    private Rules fromJson(RulesJson rulesJson) {
        // TODO: validate what was read. Maybe not here though.

        return new Rules(
                rulesJson.getLayout().getWidth(),
                rulesJson.getLayout().getHeight(),
                rulesJson.getSlots()
                        .stream()
                        .map(slotJson -> new Slot(slotJson.getName()))
                        .collect(Collectors.toList()),
                rulesJson.getSlots()
                        .stream()
                        .flatMap(slotJson -> {
                            return slotJson.getCombos()
                                    .stream()
                                    .map(comboJson -> {
                                        Slot slot = new Slot(slotJson.getName());
                                        return new SlotCombo(slot, comboJson.getLength(), comboJson.getBetMultiplier());
                                    });
                        })
                        .collect(Collectors.toList()),
                Arrays.stream(rulesJson.getLayout().getLines())
                        .map(RuleLine::new)
                        .collect(Collectors.toList())
        );
    }

    @Data
    static class RulesJson {
        private LayoutJson layout = new LayoutJson();
        private List<SlotJson> slots = Collections.emptyList();
    }

    @Data
    static class LayoutJson {
        private int width;
        private int height;
        private int[][] lines = new int[0][0];
    }

    @Data
    static class SlotJson {
        private String name = "";
        private List<SlotComboJson> combos = Collections.emptyList();
    }

    @Data
    static class SlotComboJson {
        private int length;
        private int betMultiplier;
    }
}
