package cn.weixiaochen.spring.beans;

/**
 * @author 魏小宸 2021/8/24
 */
public class BeansException extends RuntimeException {

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
