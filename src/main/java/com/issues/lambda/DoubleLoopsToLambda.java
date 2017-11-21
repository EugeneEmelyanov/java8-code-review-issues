package com.issues.lambda;

import com.issues.Permissions;
import com.issues.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The biggest and most noticeable changes in Java8 are Lambdas and Stream API introduction. Full power of stream API comes
 * with appropriate usage of Lambdas.
 */
public class DoubleLoopsToLambda {

    /**
     * Old school method to filter collection of {@code User} and get {@code Permission.MANAGER} users only.
     * See the double loop.
     * @param users
     * @return
     */
    public List<User> filterManagersBad(final List<User> users) {
        List<User> result = new ArrayList<>();
        for (User user : users) {
            for (Permissions permission : user.getPermissions()) {
                if(Permissions.MANAGER.equals(permission)) {
                    result.add(user);
                }
            }
        }
        return result;
    }

    /**
     * Slightly better way to filter collection of {@code User} and get {@code Permission.MANAGER} users only.
     * Please, note, that it is not recommended to have long lamdas due to it's really hard to debug and maintain them.
     * @param users
     * @return
     */
    public List<User> filterManagersBetter(final List<User> users) {

        return users.stream()
                .filter(user -> user.getPermissions().stream().anyMatch(p -> Permissions.MANAGER.equals(p)))
                .collect(Collectors.toList());
    }

    /**
     * Best implementation to filter collection of {@code User} and get {@code Permission.MANAGER} users only.
     * Please, note, that here we encapsulated filter {@code Predicate} into it's own function, which could be potentially
     * reused.
     * @param users
     * @return
     */
    public List<User> filterManagersBest(final List<User> users) {
        return users.stream()
                    .filter(containsPermissions(Permissions.MANAGER))
                    .collect(Collectors.toList());
    }

    private Predicate<User> containsPermissions(Permissions permissions) {
        return user -> user.getPermissions()
                        .stream()
                        .anyMatch(p -> permissions.equals(p));
    }
}
