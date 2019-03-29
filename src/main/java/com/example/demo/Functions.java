package com.example.demo;

import javax.validation.constraints.NotNull;
import java.util.Base64;

public class Functions {
    @NotNull
    public static String toBase64(@NotNull String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }
}