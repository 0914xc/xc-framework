package cn.weixiaochen.spring.core.util;

/**
 * @author 魏小宸 2021/8/28
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        return ClassUtils.class.getClassLoader();
    }

    public static Class<?> forName(String className) throws ClassNotFoundException {
        try {
            return Class.forName(className, false, getDefaultClassLoader());
        } catch (ClassNotFoundException e) {
            throw e;
        }
    }
}
