package work3.dao;

/**
 * 未查询到数据异常
 * @author jason
 */
public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException() {
    }

    public DataNotFoundException(String message) {
        super(message);
    }
}
