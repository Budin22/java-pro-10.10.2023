package org.example.test;

import org.example.exception.IllegalTestAnnotationException;
import org.example.annotation.AfterSuite;
import org.example.annotation.BeforeSuite;
import org.example.annotation.TestSuite;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class TestRunner {
    private Method beforeTests;
    private Method afterTests;

    public void run(Class<?> myClass, boolean withPriority) {
        try {
            if (myClass == null) return;
            Method[] methods = myClass.getDeclaredMethods();
            List<MyTest> tests = getTestsFromMethods(methods);

            Comparator<MyTest> comparator = Comparator.comparingInt(MyTest::getPriority);
            if (withPriority) sortTestsByPriority(tests, comparator);

            Object myObj = myClass.getDeclaredConstructor().newInstance();
            invokeTests(myObj, tests);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException |
                 InstantiationException | IllegalTestAnnotationException e) {
            System.out.println(e.getMessage());
        }
    }

    private void invokeTests(Object obj, List<MyTest> tests) throws InvocationTargetException, IllegalAccessException {
        if (tests.isEmpty()) return;
        if (beforeTests != null) beforeTests.invoke(obj);
        for (MyTest test : tests) {
            test.getMethod().invoke(obj);
        }
        if (afterTests != null) afterTests.invoke(obj);
    }

    private void sortTestsByPriority(List<MyTest> tests, Comparator<MyTest> comparator) {
        if (!tests.isEmpty()) {
            tests.sort(comparator);
        }
    }

    private List<MyTest> getTestsFromMethods(Method[] methods) throws IllegalTestAnnotationException {
        List<MyTest> tests = new LinkedList<>();
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            if (annotations.length > 1)
                throw new IllegalTestAnnotationException("Expect only 1 annotation but got " + annotations.length + " in getTestsFromMethods method");
            if (method.isAnnotationPresent(TestSuite.class)) {
                TestSuite testSuite = method.getAnnotation(TestSuite.class);
                int priority = testSuite.priority();
                MyTest test = new MyTest(priority, method);
                tests.add(test);
            } else if (method.isAnnotationPresent(BeforeSuite.class)) {
                setBeforeTests(method);
            } else if (method.isAnnotationPresent(AfterSuite.class)) {
                setAfterTests(method);
            }
        }
        return tests;
    }

    private void setBeforeTests(Method method) throws IllegalTestAnnotationException {
        if (this.beforeTests != null)
            throw new IllegalTestAnnotationException("Expect only 1 before annotation but got more then 1");
        this.beforeTests = method;
    }

    private void setAfterTests(Method method) throws IllegalTestAnnotationException {
        if (this.afterTests != null)
            throw new IllegalTestAnnotationException("Expect only 1 after annotation but got more then 1");
        this.afterTests = method;
    }
}
