package net.spanbroek.parsing;

public abstract class Parser {

    public Object parse(String input) {
        Handler handler = new Handler();
        parse(input, handler);
        return simplify(handler.lastResult);
    }

    protected abstract void parse(String input, ResultHandler handler);

    private Object simplify(Result result) {
        if (result != null && result.size() == 1) {
            return result.get(0);
        } else {
            return result;
        }
    }

    private class Handler implements ResultHandler {

        public Result lastResult;

        @Override
        public void handle(Result result, String remainder) {
            lastResult = result;
        }
    }
}
