package cn.weixiaochen.spring.core.util;

/**
 * @author 魏小宸 2021/8/28
 */
public class ClassUtils {

    public static final String PATH_SEPARATOR = "/";

    public static final String PACKAGE_SEPARATOR = ".";

    public static final String CLASS_FILE_SUFFIX = ".class";

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
        return beanClassName.substring(lastIndexOfSeparator + 1).substring(0, 1).toLowerCase() +
                beanClassName.substring(lastIndexOfSeparator + 2);
    }

    public static String convertResourcePathToClassName(String resourcePath) {
        return resourcePath.replace(PATH_SEPARATOR, PACKAGE_SEPARATOR);
    }

    public static String convertClassNameToResourcePath(String className) {
        return className.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR);
    }
}
