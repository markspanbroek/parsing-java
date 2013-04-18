package net.spanbroek.parsing;

class LiteralParser implements Parser {

    private final String literal;

    public LiteralParser(String literal) {
        this.literal = literal;
    }

    @Override
    public String parse(String input) {
        if (input.startsWith(literal)) {
            return literal;
        }
        return null;
    }
}
