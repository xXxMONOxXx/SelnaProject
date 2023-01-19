package by.mishastoma.di.utils;

import java.lang.reflect.Method;

public class MethodUtils {
    public boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) return false;
        return method.getParameterTypes().length == 1;
    }
}
