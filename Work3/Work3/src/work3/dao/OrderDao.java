package work3.dao;


import work3.domain.Order;

import java.util.ArrayList;

/**
 * @author jason
 */
public interface OrderDao {
    /**
     * 从数据库查询整个订单表
     * @param orderBy 排序方式
     * @return 返回查询到的订单列表
     */
    ArrayList<Order> selectAll(String orderBy);

    /**
     * 根据订单编号查询订单
     * @param orderId 订单编号
     * @return  返回一个订单对象
     */
    Order selectById(int orderId);

    /**
     * 更新表的某一行
     * @param order 传入需要修改的订单对象
     * @return 返回首影响的行数
     */
    int update(Order order);

    /**
     * 在表中插入新的一行
     * @param order 传入要插入表的订单对象
     * @return 返回受影响的行数
     */
    int insert(Order order);

    /**
     * 删除表中的一行
     * @param order 传入要删除的订单对象
     * @return 返回受影响的行数
     */
    int delete(Order order);

}
