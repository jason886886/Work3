package work3.service;

/**
 * 非法库存量异常
 * @author jason
 */
public class IllegalStockException extends RuntimeException{
    public IllegalStockException() {
    }

    public IllegalStockException(String message) {
        super(message);
    }
}
