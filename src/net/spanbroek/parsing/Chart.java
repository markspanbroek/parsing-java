package net.spanbroek.parsing;

import net.spanbroek.parsing.util.MultiMap;

import java.util.List;

public class Chart {

    private ResultHandlers waiting = new ResultHandlers();
    private PartialResults results = new PartialResults();

    public void waitForResults(RemainingInput input, ResultHandler handler) {
        waiting.add(input, handler);
        List<PartialResult> partials = results.get(input);
        for (int i=0; i<partials.size(); i++) {
            PartialResult partial = partials.get(i);
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
            return that instanceof PartialResult &&
                    ((PartialResult) that).result.equals(result) &&
                    ((PartialResult) that).remainder.equals(remainder);
        }
    }
}
