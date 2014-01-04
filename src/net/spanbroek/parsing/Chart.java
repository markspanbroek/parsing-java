package net.spanbroek.parsing;

import net.spanbroek.parsing.util.MultiMap;

import java.util.Set;

public class Chart {

    private ResultHandlers waiting = new ResultHandlers();
    private PartialResults results = new PartialResults();

    public void waitForResults(RemainingInput input, ResultHandler handler) {
        waiting.add(input, handler);
        Set<PartialResult> partials = results.get(input);
        for (PartialResult partial : partials) {
            handler.handle(partial.result, partial.remainder);
        }
    }

    public boolean isWaitingForResults(RemainingInput input) {
        return waiting.containsKey(input);
    }

    public void provideResult(RemainingInput input, Result result, RemainingInput remainder) {
        if (results.contains(input, result, remainder)) {
            return;
        }

        results.add(input, result, remainder);
        for (ResultHandler handler : waiting.get(input)) {
            handler.handle(result, remainder);
        }
    }

    private class ResultHandlers extends MultiMap<RemainingInput, ResultHandler> {}

    private class PartialResults extends MultiMap<RemainingInput, PartialResult> {
        public void add(RemainingInput input, Result result, RemainingInput remainder) {
            add(input, new PartialResult(result, remainder));
        }

        public boolean contains(RemainingInput input, Result result, RemainingInput remainder) {
            return get(input).contains(new PartialResult(result, remainder));
        }
    }

    private class PartialResult {
        public Result result;
        public RemainingInput remainder;

        public PartialResult(Result result, RemainingInput remainder) {
            this.result = result;
            this.remainder = remainder;
        }

        @Override
        public boolean equals(Object that) {
            return this == that ||
                    that instanceof PartialResult &&
                    remainder.equals(((PartialResult) that).remainder) &&
                    result.equals(((PartialResult) that).result);
        }

        @Override
        public int hashCode() {
            return 31 * result.hashCode() + remainder.hashCode();
        }
    }
}
