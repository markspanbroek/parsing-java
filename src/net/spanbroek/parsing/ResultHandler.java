package net.spanbroek.parsing;

public interface ResultHandler {
    void handle(Result result, RemainingInput remainder);
}
