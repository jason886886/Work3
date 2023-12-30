package work3.service;

import work3.domain.Order;

import java.util.ArrayList;

/**
 * @author jason
 */
public interface OrderService {
    /**
     * 从数据库查询整个订单表
     * @param orderBy 排序方式
     * @return 返回查询到的订单列表
     */
    ArrayList<Order> selectAllOrders(String orderBy);

    /**
     * 根据订单编号查询订单
     * @param orderId 订单编号
     * @return  返回一个订单对象
     */
    Order selectOrderById(int orderId);

    /**
     * 修改订单购买数量
     * @param order 要修改的订单对象
     * @param count 修改后的购买数量
     * @return 返回受影响行数
     */
    int changeOrderCount(Order order, int count);
    /**
     * 在表中插入新的订单
     * @param order 传入要插入表的订单对象
     * @return 返回受影响的行数
     */
    int insertOrder(Order order);

    /**
     * 删除表中的一行
     * @param order 传入要删除的订单对象
     * @return 返回受影响的行数
     */
    int deleteOrder(Order order);
}
