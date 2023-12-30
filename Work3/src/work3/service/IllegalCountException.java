package work3.service;

/**
 * 非法库存量异常
 * @author jason
 */
public class IllegalCountException extends RuntimeException{
    public IllegalCountException() {
    }

    public IllegalCountException(String message) {
        super(message);
    }
}
