package net.spanbroek.parsing;

public class Session {
    private final Trampoline trampoline;

    public Session(Trampoline trampoline) {
        this.trampoline = trampoline;
    }

    public Trampoline getTrampoline() {
        return trampoline;
    }
}
