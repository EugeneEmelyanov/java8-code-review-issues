package com.issues.tips;

import com.issues.User;

import java.util.Collections;
import java.util.Comparator;

public class GoodForgottenMethods {

    /**
     * Collection sort in reverse order.
     */
    public void sort() {
        Comparator<User> userComparator = Comparator.comparing(User::getId);

        //how to get reverse order comparator?
        Comparator<User> reverseOrderComparator = Collections.reverseOrder(userComparator);
        //second option
        Comparator<User> reversedOrderComparator1 = userComparator.reversed(); //the same as first

        //PLEASE, DO NOT SORT COLLECTIONS USING userComparator AND PERFORM Collections.reverse afterwards.
    }

    public void sort1() {
        //task is to sort users by Id, however, all users without Id should go last (e.g. as usual)
        Comparator<User> comparator = Comparator.comparing(User::getId,
                Comparator.nullsLast(Comparator.naturalOrder()));

    }
}
