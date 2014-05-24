package net.spanbroek.parsing;

import static net.spanbroek.parsing.Parsing.concat;

public class Rule extends Parser {

    private Parser expression;

    public Rule(Object... concatenation) {
        this.is(concatenation);
    }

    public void is(Object... concatenation) {
        this.expression = concat(concatenation);
    }

    public void transform(Transformation transformation) {
        expression = new TransformingParser(expression, transformation);
    }

    @Override
    protected void parse(RemainingInput input, ResultHandler handler, Session session) {
        Chart chart = session.getChartForRule(this);
        if (!chart.isWaitingForResults(input)) {
            parseUsingTrampoline(input, session);
        }
        chart.waitForResults(input, handler);
    }

    private void parseUsingTrampoline(final RemainingInput input, final Session session) {
        session.getTrampoline().schedule(() -> expression.parse(input, (result, remainder) -> {
            Chart chart = session.getChartForRule(Rule.this);
            chart.provideResult(input, result, remainder);
        }, session));
    }
}
