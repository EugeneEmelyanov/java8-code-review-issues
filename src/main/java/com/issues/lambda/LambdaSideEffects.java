package com.issues.lambda;

import com.issues.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * It's one of the most popular misuse that I'm encountering (along with large lambdas). While writing Lambda, first of all,
 * think about maintenance, supportability and side effects first. All other attributes (like amount of java code lines)
 * comes after.
 */
public class LambdaSideEffects {

    /**
     * Please, go over https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html,
     * sections "Side effects", "Ordering" and "Reduction operations".
     */

    public List<User> sideEffectLambda() {
        List<User> users = new ArrayList<>();

        Arrays.asList(1, 2, 3, 4).stream()
                .forEach(i -> {
                    users.add(User.newBuilder().withId(i).build());//accessing main method variable.
                });

        return users;
    }

    public List<User> noSideEffects() {
        return Arrays.asList(1, 2, 3, 4)
                .stream()
                .map(i -> User.newBuilder().withId(i).build())
                .collect(Collectors.toList());
    }
}
