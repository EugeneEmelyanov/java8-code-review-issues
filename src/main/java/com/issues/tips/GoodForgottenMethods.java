package com.issues.tips;

import com.issues.User;

import java.util.Collections;
import java.util.Comparator;

/**
 * Quite common there is a situation when you need to reverse sorting or put null values after non-null values.
 * Methods for such operations exists for a long time in Java, however, they are not heavily used. Below is a couple of
 * examples of how to do powerful things utilizing core Java API.
 */
public class GoodForgottenMethods {

    /**
     * Sort {@link java.util.Collection} in reverse order.
     */
    public void sort() {
        Comparator<User> userComparator = Comparator.comparing(User::getId);

        //getting reverse comparator from orginal {@link Comparator}
        Comparator<User> reverseOrderComparator = Collections.reverseOrder(userComparator);

        //Another option to get reversed comparator.
        Comparator<User> reversedOrderComparator1 = userComparator.reversed(); //the same as first

        //PLEASE, DO NOT SORT COLLECTIONS USING userComparator AND PERFORM {@link Collections#reverse} afterwards.
    }

    /**
     * Example of how to put null values after non-null values using existing {@link Comparator}.
     */
    public void sort1() {

        Comparator<User> comparator = Comparator.comparing(User::getId,
                Comparator.nullsLast(Comparator.naturalOrder()));

    }
}
