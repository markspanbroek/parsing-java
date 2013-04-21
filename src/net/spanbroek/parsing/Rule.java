package net.spanbroek.parsing;

import java.util.HashSet;
import java.util.Set;

public class Rule extends Parser {

    private Parser expression;
    private Chart chart = new Chart();
    private Set<String> parsing = new HashSet<String>();

    public Rule(Parser expression) {
        this.expression = expression;
    }

    public void is(Parser expression) {
        this.expression = expression;
    }

    @Override
    protected void parse(final String input, ResultHandler handler) {
        chart.waitForResults(input, handler);
        if (!parsing.contains(input)) {
            parsing.add(input);
            expression.parse(input, new ResultHandler() {
                @Override
                public void handle(Result result, String remainder) {
                    chart.provideResult(input, result, remainder);
                }
            });
        }
    }
}
