package net.spanbroek.parsing;

public class AlternativesParser extends Parser {

    private Parser left;
    private Parser right;

    public AlternativesParser(Parser left, Parser right) {
        this.left = left;
        this.right = right;
    }

    @Override
    protected void parse(RemainingInput input, ResultHandler handler) {
        left.parse(input, handler);
        right.parse(input, handler);
    }
}
