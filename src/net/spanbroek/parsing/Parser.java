package net.spanbroek.parsing;

public abstract class Parser {

    public Object parse(String input) {
        Session session = new Session();
        Handler handler = new Handler();
        parse(new RemainingInput(input), handler, session);
        session.getTrampoline().run();
        return simplify(handler.lastResult);
    }

    protected abstract void parse(RemainingInput input, ResultHandler handler, Session session);

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
        public void handle(Result result, RemainingInput remainder) {
            lastResult = result;
        }
    }
}
