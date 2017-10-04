package com.tsystems.ecare.dto.converter;

public interface Converter<S, T> {
    T from(S source);
    S to(T target) throws Exception;
}
