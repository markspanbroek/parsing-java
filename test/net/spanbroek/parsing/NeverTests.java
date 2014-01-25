package net.spanbroek.parsing;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static net.spanbroek.parsing.Parsing.never;

public class NeverTests {

    @Test
    public void shouldNeverParseSuccessfully() {
        assertNull(never.parse(""));
    }

}
