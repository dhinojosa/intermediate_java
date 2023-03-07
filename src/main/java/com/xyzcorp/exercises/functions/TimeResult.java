package com.xyzcorp.exercises.functions;

public class TimeResult<T> {
    private final T result;
    private final long time;

    public TimeResult(T result, long time) {
        this.result = result;
        this.time = time;
    }

    public T getResult() {
        return result;
    }

    public Long getTime() {
        return time;
    }
}
