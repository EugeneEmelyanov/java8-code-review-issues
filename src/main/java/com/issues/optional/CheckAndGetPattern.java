package com.issues.optional;

import com.issues.Permissions;
import com.issues.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CheckAndGetPattern {

    /**
     * Old way of checking something using ternary operator. Pre Java8 approach.
     * @param user
     * @return
     */
    public boolean isAdminOld(User user) {
        return (user != null && user.getPermissions() != null) ? user.getPermissions().contains(Permissions.ADMIN) : false;
    }

    /**
     * New way of checking conditions using Optionals
     * @return
     */
    public boolean isAdminNew(User user) {
        return Optional.ofNullable(user)
                .map(User::getPermissions)
                .map(p -> p.contains(Permissions.ADMIN))
                .orElse(false);
    }

    /**
     * Incorrect way of converting flow with optionals to flow without optionals. In most of the cases, such situations
     * happen if DAO/Model was refactored to Java8, however, controller logic stayed untouched.
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

//       In case requirement is to return something default (e.g. emptyList in our case),
//       map() is a good choice.
//       return getUserById(id)
//                .map(User::getPermissions)
//                .orElseGet(Collections::emptyList);
    }



    private Optional<User> getUserById(int id) {
        return Optional.of(User.newBuilder().withId(id).build());
    }

}
