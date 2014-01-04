package net.spanbroek.parsing;

public class AlternativesParser extends Parser {

    private Parser left;
    private Parser right;

    public AlternativesParser(Parser left, Parser right) {
        this.left = left;
        this.right = right;
    }

    @Override
    protected void parse(RemainingInput input, ResultHandler handler, Session session) {
        left.parse(input, handler, session);
        right.parse(input, handler, session);
    }
}
