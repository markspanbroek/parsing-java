package net.spanbroek.parsing;

import static net.spanbroek.parsing.util.Results.result;

public class EmptyParser extends Parser {
    @Override
    protected void parse(RemainingInput input, ResultHandler handler, Session session) {
        handler.handle(result(), input);
    }
}
