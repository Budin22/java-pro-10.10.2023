package org.example.test;

import java.lang.reflect.Method;

public class Test {
    private int priority;
    private Method method;

    public Test(int priority, Method method) {
        this.priority = priority;
        this.method = method;
    }

    public int getPriority() {
        return priority;
    }

    public Method getMethod() {
        return method;
    }
}
