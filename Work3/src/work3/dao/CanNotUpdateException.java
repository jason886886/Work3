package work3.dao;

/**
 * 无法执行更改异常
 * @author jason
 */
public class CanNotUpdateException extends RuntimeException{
    public CanNotUpdateException() {
    }

    public CanNotUpdateException(String message) {
        super(message);
    }
}
