package net.spanbroek.parsing;

public abstract class Parser {

    public Result parse(String input) {
        Handler handler = new Handler();
        parse(input, handler);
        return handler.lastResult;
    }

    protected abstract void parse(String input, ResultHandler handler);

    private class Handler implements ResultHandler {

        public Result lastResult;

        @Override
        public void handle(Result result, String remainder) {
            lastResult = result;
        }
    }
}
