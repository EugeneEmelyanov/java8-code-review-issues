package com.issues.lambda;

import java.util.Arrays;
import java.util.List;

public class MethodReference {

    /**
     * If you use :: syntax for non-static method references, please specify the object type of the collection correctly.
     */
    public void iteratingCollection() {


        List<B> collection = Arrays.asList(new B(), new B(), new B());

        //incorrect, it will use method from B object, as a result, "B" will be printed
        collection.stream()
                .forEach(A::method);

        //much better, however, if you would like to hide implementation details
        collection.stream()
                .forEach(B::method);

        //very clean of what method will be executed.
        collection.stream()
                .forEach(b -> b.method());


    }

    public class A {
        public void method() {
            System.out.println("A");
        }
    }

    public class B extends A {
        public void method() {
            System.out.println("B");
        }
    }

}
