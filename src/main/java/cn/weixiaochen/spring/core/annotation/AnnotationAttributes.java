package cn.weixiaochen.spring.core.annotation;

import java.lang.reflect.Array;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 魏小宸 2021/8/29
 */
public class AnnotationAttributes extends LinkedHashMap<String, Object> {

    public AnnotationAttributes() {
    }

    public AnnotationAttributes(Map<String, Object> map) {
        super(map);
    }
    public String getString(String attributeName) {
        return getRequiredAttribute(attributeName, String.class);
    }

    public String[] getStringArray(String attributeName) {
        return getRequiredAttribute(attributeName, String[].class);
    }

    public boolean getBoolean(String attributeName) {
        return getRequiredAttribute(attributeName, Boolean.class);
    }

    @SuppressWarnings("unchecked")
    public <T> Class<? extends T> getClass(String attributeName) {
        return getRequiredAttribute(attributeName, Class.class);
    }

    public Class<?>[] getClassArray(String attributeName) {
        return getRequiredAttribute(attributeName, Class[].class);
    }

    @SuppressWarnings("unchecked")
    private <T> T getRequiredAttribute(String attributeName, Class<T> expectedType) {
        Object value = get(attributeName);
        if (!expectedType.isInstance(value) && expectedType.isArray() &&
                expectedType.getComponentType().isInstance(value)) {
            Object array = Array.newInstance(expectedType.getComponentType(), 1);
            Array.set(array, 0, value);
            value = array;
        }
        return (T) value;
    }

    public static AnnotationAttributes fromMap(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        if (map instanceof AnnotationAttributes) {
            return (AnnotationAttributes) map;
        }
        return new AnnotationAttributes(map);
    }
}
