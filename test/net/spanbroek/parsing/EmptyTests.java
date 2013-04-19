package net.spanbroek.parsing;

import org.junit.Test;

import static net.spanbroek.parsing.Parsing.concat;
import static net.spanbroek.parsing.Parsing.empty;
import static net.spanbroek.parsing.Parsing.literal;
import static net.spanbroek.parsing.util.Results.result;
import static org.junit.Assert.assertEquals;

public class EmptyTests {

    private Parser parser = empty;

    @Test
    public void shouldParseEmpty() {
        assertEquals(result(), parser.parse(""));
    }

    @Test
    public void shouldNotAddToResult() {
        parser = concat(empty, literal("a"));
        assertEquals(result("a"), parser.parse("a"));
    }
}
