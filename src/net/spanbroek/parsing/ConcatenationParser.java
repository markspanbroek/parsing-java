package net.spanbroek.parsing;

import static net.spanbroek.parsing.util.Results.combine;

public class ConcatenationParser extends Parser {

    private Parser left;
    private Parser right;

    public ConcatenationParser(Parser left, Parser right) {
        this.left = left;
        this.right = right;
    }

    @Override
    protected void parse(RemainingInput input, final Trampoline trampoline, final ResultHandler handler) {
        left.parse(input, trampoline, new ResultHandler() {
            @Override
            public void handle(final Result leftResult, RemainingInput leftRemainder) {
                right.parse(leftRemainder, trampoline, new ResultHandler() {
                    @Override
                    public void handle(Result rightResult, RemainingInput rightRemainder) {
                        handler.handle(combine(leftResult, rightResult), rightRemainder);
                    }
                });
            }
        });
    }
}
