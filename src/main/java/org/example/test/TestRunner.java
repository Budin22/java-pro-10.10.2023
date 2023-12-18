package org.example.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class TestRunner {
    private Method beforeTests;
    private Method afterTests;

    public void start(Class<?> myClass) {
        try {
            if (myClass == null) throw new NullPointerException("Class should be not null");
            Method[] methods = myClass.getDeclaredMethods();
            List<Test> tests = getTestsFromMethods(methods);

            Comparator<Test> comparator = (t1, t2) -> Integer.compare(t1.getPriority(), t2.getPriority());
            sortTestsByPriority(tests, comparator);

            Object myObj = myClass.getDeclaredConstructor().newInstance();
            invokeTests(myObj, tests);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    private void invokeTests(Object obj, List<Test> tests) {
        try {
            if (tests.isEmpty()) return;
            if (beforeTests != null) beforeTests.invoke(obj);
            for (Test test : tests) {
                test.getMethod().invoke(obj);
            }
            if (afterTests != null) afterTests.invoke(obj);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void sortTestsByPriority(List<Test> tests, Comparator<Test> comparator) {
        if (!tests.isEmpty()) {
            tests.sort(comparator);
        }
    }

    private void checkAnnotationsForException(boolean isBefore, boolean isTest, boolean isAfter) {
        if (isBefore && isTest)
            throw new IllegalArgumentException("Got @BeforeSuite and @Test annotations but expect 1");
        if (isAfter && isTest)
            throw new IllegalArgumentException("Got @AfterSuite and @Test annotations but expect 1");
        if (isAfter && isBefore)
            throw new IllegalArgumentException("Got @AfterSuite and @BeforeSuite annotations but expect 1");
        if (isBefore && beforeTests != null)
            throw new IllegalArgumentException("Got 2 @BeforeSuite annotation and expect 1");
        if (isAfter && afterTests != null)
            throw new IllegalArgumentException("Got 2 @AfterSuite annotation and expect 1");
    }

    private List<Test> getTestsFromMethods(Method[] methods) {
        List<Test> tests = new LinkedList<>();
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();

            for (Annotation ann : annotations) {
                boolean isBefore = ann.toString().contains("BeforeSuite");
                boolean isAfter = ann.toString().contains("AfterSuite");
                boolean isTest = ann.toString().contains("Test");

                checkAnnotationsForException(isBefore, isTest, isAfter);
                if (isBefore) beforeTests = method;
                if (isAfter) afterTests = method;
                if (isTest) {
                    String priorityString = ann.toString().substring(ann.toString().indexOf("=") + 1, ann.toString().indexOf(")"));
                    int priority = Integer.parseInt(priorityString);
                    Test test = new Test(priority, method);
                    tests.add(test);
                }
            }
        }
        return tests;
    }
}
