package net.spanbroek.parsing;

import org.junit.Test;

import java.util.List;

import static net.spanbroek.parsing.Parsing.choice;
import static net.spanbroek.parsing.Parsing.rule;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AlternativesTests {

    private Parser parser = choice("foo", "bar", "baz");

    @Test
    public void shouldParseAlternatives() {
        assertEquals("foo", parser.parse("foo"));
        assertEquals("bar", parser.parse("bar"));
        assertEquals("baz", parser.parse("baz"));
    }

    @Test
    public void shouldFailToParseAlternatives() {
        assertNull(parser.parse("qux"));
    }

    @Test
    public void shouldHandleChoiceOfOne() {
        parser = choice("foo");
        assertEquals("foo", parser.parse("foo"));
    }

    @Test
    public void shouldHandleEmptyChoice() {
        parser = choice();
        assertNull(parser.parse(""));
    }

    @Test
    public void shouldPreferFirstMatchingAlternative()
    {
        Rule bar = rule("foo");
        Rule baz = rule("foo");
        bar.transform(new Transformation() {
            @Override
            public Object transform(List<Object> result, Context context) {
                return "bar";
            }
        });
        baz.transform(new Transformation() {
            @Override
            public Object transform(List<Object> result, Context context) {
                return "baz";
            }
        });

        parser = choice(bar, baz);

        assertEquals("bar", parser.parse("foo"));
    }

}
