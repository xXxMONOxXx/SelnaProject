package by.mishastoma.di;

import by.mishastoma.di.annotations.Autowire;
import by.mishastoma.di.annotations.Component;
import by.mishastoma.di.annotations.Value;
import by.mishastoma.di.exception.DIException;
import by.mishastoma.di.utils.MethodUtils;
import by.mishastoma.di.utils.PropReader;
import by.mishastoma.di.utils.StringUtils;
import lombok.SneakyThrows;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class AnnotationConfigApplicationContext {
    public static final String DEFAULT_PROPERTIES_FILE_NAME = "application.properties";
    private final Reflections reflections;
    private final StringUtils stringUtils = new StringUtils();
    private final PropReader propReader = new PropReader();
    private final MethodUtils methodUtils = new MethodUtils();
    private final Set<Class<?>> classes;
    private Map<String, Object> annotatedObjects = new HashMap<>();
    private Map<String, String> objectsMap = new HashMap<>();
    public AnnotationConfigApplicationContext(Class<?> context) throws DIException {
        reflections = new Reflections(context, new SubTypesScanner(false));
        try {
            classes = getClasses();
            objectsMap = propReader.readPropFile(DEFAULT_PROPERTIES_FILE_NAME);
            createComponents();
        } catch (FileNotFoundException e) {
            throw new DIException(e.getMessage());
        }
    }

    @SneakyThrows
    public <T> T getBean(Class<T> tClass) {
        return (T) annotatedObjects.get(getImplClass(tClass).getName());
    }

    private Set<Class<?>> getClasses() {
        return reflections.getSubTypesOf(Object.class);
    }

    private void createComponents() throws DIException{
        for (Class<?> clas : classes) {
            if (clas.isAnnotationPresent(Component.class)) {
                createObject(clas);
            }
        }
    }

    private <T> T createObject(Class<T> type) throws DIException{
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = getImplClass(type);
            return createObject(implClass);
        } else {
            Object obj = annotatedObjects.get(type.getName());
            if (obj == null) {
                Object createdObj = createObjectFromImplClass(implClass);
                annotatedObjects.put(createdObj.getClass().getName(), createdObj);
                return (T) createdObj;
            } else {
                return (T) obj;
            }
        }
    }

    @SneakyThrows
    private Object createObjectFromImplClass(Class<?> clas) {
        Object obj = hasWiredConstructor(clas) ? createObjectWithWiredConstructor(clas) : clas.getDeclaredConstructor().newInstance();
        fillAnnotatedMethods(obj);
        fillAnnotatedFields(obj);
        return obj;
    }

    private boolean hasWiredConstructor(Class<?> clas) {
        return Arrays.stream(clas.getDeclaredConstructors()).filter(x -> x.isAnnotationPresent(Autowire.class)).toArray().length > 0;
    }

    @SneakyThrows
    private Object createObjectWithWiredConstructor(Class<?> clas) {
        short numberOfWiredConstructors = 0;
        Object obj = null;
        for (Constructor<?> constructor : clas.getDeclaredConstructors()) {
            if (constructor.isAnnotationPresent(Autowire.class)) {
                obj = constructor.newInstance(createParametersForConstructor(constructor));
                numberOfWiredConstructors++;
            }
        }
        if (numberOfWiredConstructors == 1) {
            return obj;
        } else {
            throw new DIException("More than one constructor in class " + clas.getName() + " is autowired.");
        }
    }


    private <T> Class<? extends T> getImplClass(Class<T> ifc) throws DIException{
        Set<Class<? extends T>> subClasses = reflections.getSubTypesOf(ifc);
        if (subClasses.size() > 1) {
            throw new DIException("Number of implementation of " + ifc + " is not 1.");
        }
        return subClasses.iterator().next();
    }

    @SneakyThrows
    private void fillAnnotatedFields(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowire.class)) {
                field.setAccessible(true);
                Object objectField = getObject(field.getType());
                field.set(obj, objectField);
            } else if (field.isAnnotationPresent(Value.class)) {
                Value annotation = field.getAnnotation(Value.class);
                if (annotation != null) {
                    if (!stringUtils.isCorrectAnnotationValue(annotation.value())) {
                        throw new DIException("Incorrect value in " + obj.getClass().getName() + " field: " + field.getName());
                    }
                    String value = annotation.value().isEmpty() ?
                            objectsMap.get(field.getName()) : objectsMap.get(stringUtils.convertAnnotationValue(annotation.value()));
                    field.setAccessible(true);
                    field.set(obj, value);
                }
            }
        }
    }

    @SneakyThrows
    private void fillAnnotatedMethods(Object obj) {
        for (Method method : obj.getClass().getMethods()) {
            if (method.isAnnotationPresent(Autowire.class) && methodUtils.isSetter(method)) {
                Parameter[] parameters = method.getParameters();
                if (parameters.length == 1) {
                    createFieldFromParameter(parameters[0], obj);
                } else {
                    throw new DIException("Number of parameters for setter: " + method.getName() + " is not equal to one.");
                }
            }
        }
    }

    @SneakyThrows
    private void createFieldFromParameter(Parameter parameter, Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.getType().equals(parameter.getType())) {
                Object objectField = getObject(field.getType());
                field.setAccessible(true);
                field.set(obj, objectField);
            }
        }
    }

    private Object[] createParametersForConstructor(Constructor<?> constructor) throws DIException{
        Parameter[] parameters = constructor.getParameters();
        List<Object> objects = new ArrayList<>();
        for (Parameter parameter : parameters) {
            objects.add(createObject(parameter.getType()));
        }
        return objects.toArray();
    }

    private <T> T getObject(Class<T> type) throws DIException{
        if (annotatedObjects.containsKey(type.getName())) {
            return (T) annotatedObjects.get(type);
        }
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = getImplClass(type);
        }
        return createObject(implClass);
    }
}
