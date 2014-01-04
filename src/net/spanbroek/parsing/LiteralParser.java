package net.spanbroek.parsing;

import static net.spanbroek.parsing.util.Results.result;

class LiteralParser extends Parser {

    private final String literal;

    public LiteralParser(String literal) {
        this.literal = literal;
    }

    @Override
    protected void parse(RemainingInput input, ResultHandler handler, Session session) {
        if (input.startsWith(literal)) {
            handler.handle(result(literal), input.shift(literal.length()));
        }
    }
}
