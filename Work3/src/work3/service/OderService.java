package work3.service;

import work3.domain.Order;

import java.util.ArrayList;

/**
 * @author jason
 */
public interface OderService {
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


}
