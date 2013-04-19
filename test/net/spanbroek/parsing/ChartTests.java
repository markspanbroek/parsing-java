package net.spanbroek.parsing;

import org.junit.Test;

import static net.spanbroek.parsing.util.Results.result;
import static org.mockito.Mockito.*;

public class ChartTests {

    private Chart chart = new Chart();
    private Result result = result("result");
    private String remainder = "remainder";
    private ResultHandler handler = mock(ResultHandler.class);

    @Test
    public void shouldNotifyWhenAResultIsProvided() {
        chart.waitForResults("input", handler);
        chart.provideResult("input", result, remainder);

        verify(handler).handle(result, remainder);
    }

    @Test
    public void shouldNotNotifyAboutResultForADifferentInput() {
        chart.waitForResults("input", handler);
        chart.provideResult("different input", result, remainder);

        verify(handler, never()).handle(result, remainder);
    }

    @Test
    public void shouldNotifyAboutMultipleResults() {
        chart.waitForResults("input", handler);
        chart.provideResult("input", result, "remainder1");
        chart.provideResult("input", result, "remainder2");

        verify(handler).handle(result, "remainder1");
        verify(handler).handle(result, "remainder2");
    }

    @Test
    public void shouldNotifyAboutResultsFromThePast() {
        chart.provideResult("input", result, "remainder1");
        chart.provideResult("input", result, "remainder2");
        chart.waitForResults("input", handler);

        verify(handler).handle(result, "remainder1");
        verify(handler).handle(result, "remainder2");
    }

    @Test
    public void shouldNotifyMultipleListeners() {
        ResultHandler handler1 = mock(ResultHandler.class);
        ResultHandler handler2 = mock(ResultHandler.class);

        chart.waitForResults("input", handler1);
        chart.waitForResults("input", handler2);
        chart.provideResult("input", result, remainder);

        verify(handler1).handle(result, remainder);
        verify(handler2).handle(result, remainder);
    }

    @Test
    public void shouldNotifyAboutAResultOnlyOnce() {
        chart.waitForResults("input", handler);
        chart.provideResult("input", result, remainder);
        chart.provideResult("input", result, remainder);

        verify(handler, times(1)).handle(result, remainder);
    }
}
