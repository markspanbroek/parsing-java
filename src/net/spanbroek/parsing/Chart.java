package net.spanbroek.parsing;

import net.spanbroek.parsing.util.MultiMap;

public class Chart {

    private ResultHandlers waiting = new ResultHandlers();
    private PartialResults results = new PartialResults();

    public void waitForResults(String input, ResultHandler handler) {
        waiting.add(input, handler);
        for (PartialResult partial : results.get(input)) {
            handler.handle(partial.result, partial.remainder);
        }
    }

    public void provideResult(String input, Result result, String remainder) {
        if (results.contains(input, result, remainder)) {
            return;
        }

        results.add(input, result, remainder);
        for (ResultHandler handler : waiting.get(input)) {
            handler.handle(result, remainder);
        }
    }

    private class ResultHandlers extends MultiMap<String, ResultHandler> {}

    private class PartialResults extends MultiMap<String, PartialResult> {
        public void add(String input, Result result, String remainder) {
            add(input, new PartialResult(result, remainder));
        }

        public boolean contains(String input, Result result, String remainder) {
            return get(input).contains(new PartialResult(result, remainder));
        }
    }

    private class PartialResult {
        public Result result;
        public String remainder;

        public PartialResult(Result result, String remainder) {
            this.result = result;
            this.remainder = remainder;
        }

        @Override
        public boolean equals(Object that) {
            return that instanceof PartialResult &&
                    ((PartialResult) that).result.equals(result) &&
                    ((PartialResult) that).remainder.equals(remainder);
        }
    }
}
