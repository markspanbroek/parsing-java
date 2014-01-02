package net.spanbroek.parsing;

public class AlternativesParser extends Parser {

    private Parser left;
    private Parser right;

    public AlternativesParser(Parser left, Parser right) {
        this.left = left;
        this.right = right;
    }

    @Override
    protected void parse(RemainingInput input, Trampoline trampoline, ResultHandler handler) {
        left.parse(input, trampoline, handler);
        right.parse(input, trampoline, handler);
    }
}
