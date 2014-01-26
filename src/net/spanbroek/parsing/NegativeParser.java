package net.spanbroek.parsing;

import java.util.Arrays;
import java.util.List;

import static net.spanbroek.parsing.util.Results.result;

public class NegativeParser extends Parser {
    private List<Character> characters;

    public NegativeParser(Character... characters) {
        this.characters = Arrays.asList(characters);
    }

    @Override
    protected void parse(RemainingInput input, ResultHandler handler, Session session) {
        char character = input.firstCharacter();
        if (!characters.contains(character)) {
            handler.handle(result("" + character), input.shift(1));
        }
    }
}
