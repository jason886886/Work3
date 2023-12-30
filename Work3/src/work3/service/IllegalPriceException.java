package work3.service;

/**
 * 非法价格异常
 * @author jason
 */
public class IllegalPriceException extends RuntimeException{
    public IllegalPriceException() {
    }

    public IllegalPriceException(String message) {
        super(message);
    }
}
