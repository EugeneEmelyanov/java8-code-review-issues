package com.issues.lambda;

import java.util.Optional;

public class MethodReferences {

    /**
     * Instead of lambdas you can use method references. In some cases, it may increase method readability.
     */

    public void processStringNotRecommended(String s) {
        Optional.ofNullable(s)
                .map(str -> preprocessString(str))
                .ifPresent(str -> System.out.println(s));

    }

    public void processStringRecommended(String s) {
        Optional.ofNullable(s)
                .map(this::preprocessString)
                .ifPresent(System.out::println);
    }

    private String preprocessString(String s) {
        return s.replace("a", "A");
    }
}
