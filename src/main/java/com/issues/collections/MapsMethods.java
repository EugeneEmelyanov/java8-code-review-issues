package com.issues.collections;

import com.issues.User;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java8 added a couple of useful methods into Collection framework. Most of them a highly connected with lambda introduction,
 * as a result, it changes standard pre-java8 constructions.
 *
 * Please, take a look at:
 * https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true
 */

public class MapsMethods {

    /**
     * Examples of {@link Map#computeIfAbsent}, {@link Map#compute} and
     * {@link Map#merge}
     *
     * !!!!Please, avoid old style usage of {@link Map#containsKey(Object)}
     */

    private Map<Integer, List<User>> usersByPermissions = new HashMap<>();

    public void addUserToMap(Integer key, List<User> values) {
        //old-school, pre java8 world
        if (!usersByPermissions.containsKey(key)) {
            usersByPermissions.put(key, new ArrayList<>());
        }
        usersByPermissions.get(key).addAll(values);

        //new wave, java8+ world
        usersByPermissions
                .computeIfAbsent(key, ArrayList<User>::new)
                .addAll(values);

        //another example
        usersByPermissions
                .merge(key, values, (list1, list2) -> Stream.of(list1, list2)
                                                        .flatMap(Collection::stream)
                                                        .collect(Collectors.toList()));
    }

    /**
     * Lazy operations supported as well.
     * Please, note how elegant lazy computations could be incorporated into {@link Map#computeIfAbsent(Object, Function)}
     * method
     */
    public void addUsersLazy(Integer key) {
        //old-school, pre java8 world
        if (!usersByPermissions.containsKey(key)) {
            usersByPermissions.put(key, heavyMethodToGetUsers(key));
        }

        //bad code, please, do not use it. It will invoke heavy method even if key is present in <code>userByPermissions</code>
        //maps
        usersByPermissions.putIfAbsent(key, heavyMethodToGetUsers(key));

        //good example of lazy execution
        usersByPermissions.computeIfAbsent(key, this::heavyMethodToGetUsers);
    }


    private List<User> heavyMethodToGetUsers(Integer userPermission) {
        return new ArrayList<>();
    }
}
