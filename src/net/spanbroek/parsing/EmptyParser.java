package net.spanbroek.parsing;

import static net.spanbroek.parsing.util.Results.result;

public class EmptyParser extends Parser {
    @Override
    protected void parse(String input, ResultHandler handler) {
        handler.handle(result(), input);
    }
}
