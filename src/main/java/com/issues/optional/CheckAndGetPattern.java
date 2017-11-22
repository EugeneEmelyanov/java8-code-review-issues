package com.issues.optional;

import com.issues.Permissions;
import com.issues.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * {@link Optional} is an API addition for Java8. Main purpose is to reduce redundant null pointer checks
 * (and the amount of {@link NullPointerException}s).
 *
 * Two main points to remember regarding optionals:
 * 1) Do not use {@link Optional} as method arguments.
 * 2) Do not call {@link Optional#get()} or {@link Optional#isPresent()} right where you get Optional. Think about more
 * elegant solution.
 *
 *  Some useful tips regarding Java8 best practices: https://blog.jetbrains.com/idea/2016/07/java-8-top-tips/
 */
public class CheckAndGetPattern {

    /**
     * Old way of checking something using ternary operator.
     * @param user
     * @return
     */
    public boolean isAdminOldStyle(User user) {
        return (user != null && user.getPermissions() != null) ? user.getPermissions().contains(Permissions.ADMIN) : false;
    }

    /**
     * New way of checking conditions using {@link Optional}
     * @return
     */
    public boolean isAdminNewStyle(User user) {
        return Optional.ofNullable(user)
                .map(User::getPermissions)
                .map(p -> p.contains(Permissions.ADMIN))
                .orElse(false);
    }

    /**
     * Incorrect way of converting program flow to use {@link Optional}. In most of the cases, such situations
     * happen when DAO/Model was refactored to Java8, however, controller logic stayed as-is.
     * @param id
     * @return
     */
    public List<Permissions> getPermissionsByUserIdIncorrect(int id) {
        Optional<User> user = getUserById(id);

        if (user.isPresent()) {
            return user.get().getPermissions();
        } else {
            throw new IllegalArgumentException("User with id=" + id + " not found.");
        }
    }

    /**
     * orElseThrow will actually unwrap Optional for us.
     * @param id
     * @return
     */
    public List<Permissions> getPermissionsByUserId(int id) {
        return getUserById(id)
                .orElseThrow(()->new IllegalArgumentException("User with id=" + id + " not found."))
                .getPermissions();

        /**
         * In case requirements is to return some default value, use {@link Optional#orElseGet(Supplier)}
         */
//       return getUserById(id)
//                .map(User::getPermissions)
//                .orElseGet(Collections::emptyList);
    }



    private Optional<User> getUserById(int id) {
        return Optional.of(User.newBuilder().withId(id).build());
    }

}
