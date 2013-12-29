package net.spanbroek.parsing;

import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

public class TrampolineTests {

    private final Trampoline trampoline = new Trampoline();

    @Test
    public void shouldRunScheduledItem() {
        Runnable item = mock(Runnable.class);
        trampoline.schedule(item);

        verify(item, never()).run();
        trampoline.run();
        verify(item).run();
    }

    @Test
    public void shouldRunScheduledItemsInOrder() {
        Runnable item1 = mock(Runnable.class);
        Runnable item2 = mock(Runnable.class);
        InOrder order = inOrder(item1, item2);

        trampoline.schedule(item1);
        trampoline.schedule(item2);
        trampoline.run();

        order.verify(item1).run();
        order.verify(item2).run();
    }

}
