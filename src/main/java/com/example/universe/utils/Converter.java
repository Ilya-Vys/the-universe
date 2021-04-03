package com.example.universe.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Converter<T> {

    public static<T> String toJSON(T t) throws IOException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(t);
    }
}
