package net.spanbroek.parsing;

import java.util.HashSet;
import java.util.Set;

public class Rule extends Parser {

    private Parser expression;
    private Chart chart = new Chart();
    private Set<RemainingInput> parsing = new HashSet<RemainingInput>();

    public Rule(Parser expression) {
        this.expression = expression;
    }

    public void is(Parser expression) {
        this.expression = expression;
    }

    public void transform(Transformation transformation) {
        expression = Parsing.transform(expression, transformation);
    }

    @Override
    protected void parse(final RemainingInput input, ResultHandler handler, Session session) {
        chart.waitForResults(input, handler);
        if (!parsing.contains(input)) {
            parsing.add(input);
            parseUsingTrampoline(input, session);
        }
    }

    private void parseUsingTrampoline(final RemainingInput input, final Session session) {
        session.getTrampoline().schedule(new Runnable() {
            @Override
            public void run() {
                expression.parse(input, new ResultHandler() {
                    @Override
                    public void handle(Result result, RemainingInput remainder) {
                        chart.provideResult(input, result, remainder);
                    }
                }, session);
            }
        });
    }

}
