package net.spanbroek.parsing;

import static net.spanbroek.parsing.util.Results.result;

public class EmptyParser extends Parser {
    @Override
    protected void parse(RemainingInput input, Trampoline trampoline, ResultHandler handler) {
        handler.handle(result(), input);
    }
}
