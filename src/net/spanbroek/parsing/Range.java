package net.spanbroek.parsing;

import static net.spanbroek.parsing.util.Results.result;

public class Range extends Parser {

    private char begin;
    private char end;

    public Range(char begin, char end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected void parse(RemainingInput input, ResultHandler handler) {
        if (input.length() < 1) {
            return;
        }

        char character = input.firstCharacter();
        if (begin <= character && character <= end) {
            Result result = result("" + character);
            handler.handle(result, input.shift(1));
        }
    }
}
