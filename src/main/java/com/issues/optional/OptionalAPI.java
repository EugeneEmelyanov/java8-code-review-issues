package com.issues.optional;

import com.issues.User;

import java.util.Optional;

public class OptionalAPI {

    /**
     * Optional API is quite new. However, general rule is that if you are trying to call isPresent more than once,
     * there should be a better way to implement the same functionality.
     * @param user
     * @return
     */
    public Integer getGrandParentIdIncorrect(User user) {
        if (Optional.ofNullable(user).isPresent() ) {
            if (user.getParent().isPresent()) {
                if (user.getParent().get().getParent().isPresent()) {
                    return user.getParent().get().getParent().get().getId();
                }
            }
        }
        return null;
    }

    /**
     * Better way of using optionals is to perform implicit checks using map function. However, disadvantage of map() is that it
     * will not unwrap optionals for us. And in general code looks very ugly.
     * @param user
     * @return
     */
    public Integer getGrandParentIdBad(User user) {
        return Optional.ofNullable(user)
                .map(User::getParent)
                .map(u -> u.orElse(User.newBuilder().build()).getParent())
                .map(u -> u.orElse(User.newBuilder().build()).getId())
                .orElse(null);
    }


    /**
     * Correct example of using Optional APIs. flatMap() function has advantage over map in our case due to it accepts Optional
     * as parameterized type and does not wrap the result with an additional optional.
     * @param user
     * @return
     */
    public Integer getGrandParentId(User user) {
        return Optional.ofNullable(user)
                .flatMap(User::getParent)
                .flatMap(User::getParent)
                .map(User::getId)
                .orElse(null);
    }
}
