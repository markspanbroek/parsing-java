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
    protected void parse(RemainingInput input, final ResultHandler handler, final Session session) {
        left.parse(
                input,
                (Result leftResult, RemainingInput leftRemainder) ->
                        right.parse(leftRemainder, (rightResult, rightRemainder) ->
                                handler.handle(combine(leftResult, rightResult), rightRemainder), session),
                session
        );
    }
}
