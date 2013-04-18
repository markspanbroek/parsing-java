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
    protected void parse(String input, final ResultHandler handler) {
        left.parse(input, new ResultHandler() {
            @Override
            public void handle(final Result leftResult, String leftRemainder) {
                right.parse(leftRemainder, new ResultHandler() {
                    @Override
                    public void handle(Result rightResult, String rightRemainder) {
                        handler.handle(combine(leftResult, rightResult), rightRemainder);
                    }
                });
            }
        });
    }
}
