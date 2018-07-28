package com.gangster.cms.common.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Func {
    public static <R> R get(Supplier<R> supplier) {
        return supplier.get();
    }

    public static <T> void accept(T t, Consumer<T> consumer) {
        consumer.accept(t);
    }
}
