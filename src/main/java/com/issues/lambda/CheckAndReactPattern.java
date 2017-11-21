package com.issues.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


/**
 * Lambda provides very elegant way to defer calculations till the point they actually needed. E.g. instead of accepting
 * collections you can provide overloaded method that accept {@link Supplier} if your business logic contains cases when you
 * do not actually need the data immediately.
 */
public class CheckAndReactPattern {

    /**
     * We have some Service with complex business logic, which accepts {@code List<Domain>} as a parameter.
     */
    public static class BusinessLogicService {
        public boolean checkCondition() {
            return false;
        }

        public void performCalculations(List<Domain> collection) {

        }
    }

    /**
     * The same service which accepts {@code Supplier<List<Domain>>} as a parameter.
     */
    public static class BusinessLogicServiceLazy {
        public boolean checkCondition() {
            return false;
        }

        public void performCalculations(Supplier<List<Domain>> domainSupplier) {
            if (checkCondition()) {
                List<Domain> domainList = domainSupplier.get(); //only at that point lambda will be invoked.
                //the rest of the logic goes here
            }
        }
    }

    /**
     * Dummy domain model class.
     */
    public static class Domain {

    }

    /**
     * We have a controller method that would like to check condition and perform logic after that.
     */

    public static class ControllerBad {

        protected BusinessLogicService service;

        /**
         * Not recommended due to you are moving business logic into controller. Better to refactor
         * {@link BusinessLogicService#performCalculations(List)} to check condition first and perform calculations afterwards.
         */
        public void doControllerLogicWithoutLazyCalc() {
            List<Domain> domainCollection = new ArrayList<>(); //logic to get list of DomainObjects
            if (service.checkCondition()) { //condition check, which is part of the business logic.
                service.performCalculations(domainCollection);//performing business logic.
            }
        }
    }

    /**
     * The same as {@link ControllerBad#doControllerLogicWithoutLazyCalc()}, however, this time, it uses deferred calculations.
     */
    public static class ControllerDeferred {

        protected BusinessLogicServiceLazy service;

        public void doControllerLogicGood() {
            //by providing {@code Supplier} via lambda you can defer the calculations till the point when the data is actually
            // needed. E.g. there is not sense in getting all the domain objects if controller condition is falsy.
            Supplier<List<Domain>> domainSupplier = () -> new ArrayList<Domain>();

            service.performCalculations(domainSupplier);
        }

    }




}
