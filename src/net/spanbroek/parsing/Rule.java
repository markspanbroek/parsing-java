package net.spanbroek.parsing;

import static net.spanbroek.parsing.Parsing.concat;

public class Rule extends Parser {

    private Parser expression;

    public Rule(Parser expression) {
        this.expression = expression;
    }

    public void is(Parser... concatenation) {
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
        session.getTrampoline().schedule(new Runnable() {
            @Override
            public void run() {
                expression.parse(input, new ResultHandler() {
                    @Override
                    public void handle(Result result, RemainingInput remainder) {
                        Chart chart = session.getChartForRule(Rule.this);
                        chart.provideResult(input, result, remainder);
                    }
                }, session);
            }
        });
    }
}
