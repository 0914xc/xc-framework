package cn.weixiaochen.spring.core.util;

/**
 * @author 魏小宸 2021/8/28
 */
public class ClassUtils {

    private static final String PACKAGE_SEPARATOR = ".";

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

    public static String getShortName(String beanClassName) {
        int lastIndexOfSeparator = beanClassName.lastIndexOf(PACKAGE_SEPARATOR);
        return beanClassName.substring(lastIndexOfSeparator + 1).substring(0, 1).toLowerCase()
                + beanClassName.substring(lastIndexOfSeparator + 2);
    }
}
