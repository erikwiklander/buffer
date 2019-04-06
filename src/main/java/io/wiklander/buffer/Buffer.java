package io.wiklander.buffer;

import com.google.common.collect.Iterators;
import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Buffer<T> {

    private final ArrayBlockingQueue<T> buffer;

    public Buffer(int capacity, Consumer<? super List<T>> consumer) {
        this.buffer = new ArrayBlockingQueue<>(capacity * 2);
        Executors.newSingleThreadExecutor()
                .submit(() ->
                        Iterators.partition(Stream.generate(this::take).iterator(), capacity)
                                .forEachRemaining(consumer)
                );
    }

    @SneakyThrows
    public void put(T value) {
        buffer.put(value);
    }

    @SneakyThrows
    private T take() {
        return buffer.take();
    }

}
