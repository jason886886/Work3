package work3.service;

import work3.dao.OrderDaoImpl;
import work3.domain.Good;
import work3.domain.Order;
import work3.utils.JdbcUtils;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author jason
 */
public class OrderServiceImpl implements OrderService {
    private Connection conn;
    private OrderDaoImpl orderDao;
    private final GoodServiceImpl goodService = new GoodServiceImpl();

    @Override
    public ArrayList<Order> selectAllOrders(String orderBy) {
        try {
            conn = JdbcUtils.getConnection();
            orderDao = new OrderDaoImpl(conn);
            return orderDao.selectAll(orderBy);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtils.release(conn);
        }
    }

    @Override
    public Order selectOrderById(int orderId) {
        try {
            conn = JdbcUtils.getConnection();
            orderDao = new OrderDaoImpl(conn);
            return orderDao.selectById(orderId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtils.release(conn);
        }
    }

    @Override
    public int changeOrderCount(Order order, int count) {
        try {
            int result;
            conn = JdbcUtils.getConnection();
            JdbcUtils.startAffair(conn);
            orderDao = new OrderDaoImpl(conn);
            Good good = goodService.selectGoodById(order.getGoodId());
            int changeCount, orderCount = order.getCount();
            if (order.getCount() <= count) {
                changeCount = count - orderCount;
                goodService.saleGood(good, changeCount);
                order.setCount(count);
                order.setOrderPrice(order.getOrderPrice() + good.getGoodPrice() * changeCount);
                result = orderDao.update(order);
            } else {
                changeCount = orderCount - count;
                goodService.replenishStock(good, changeCount);
                order.setCount(count);
                order.setOrderPrice(order.getOrderPrice() - good.getGoodPrice() * changeCount);
                result = orderDao.update(order);
            }
            JdbcUtils.endAffair(conn);
            return result;
        } catch (Exception e) {
            JdbcUtils.rollBack(conn);
            e.printStackTrace();
            return 0;
        } finally {
            JdbcUtils.release(conn);
        }
    }

    @Override
    public int insertOrder(Order order) {
        try {
            conn = JdbcUtils.getConnection();
            JdbcUtils.startAffair(conn);
            orderDao = new OrderDaoImpl(conn);
            Good good = goodService.selectGoodById(order.getGoodId());
            goodService.saleGood(good, order.getCount());
            int result = orderDao.insert(order);
            JdbcUtils.endAffair(conn);
            return result;
        } catch (Exception e) {
            JdbcUtils.rollBack(conn);
            e.printStackTrace();
            return 0;
        } finally {
            JdbcUtils.release(conn);
        }
    }

    @Override
    public int deleteOrder(Order order) {
        try{
            conn = JdbcUtils.getConnection();
            JdbcUtils.startAffair(conn);
            orderDao = new OrderDaoImpl(conn);
            Good good = goodService.selectGoodById(order.getGoodId());
            goodService.replenishStock(good, order.getCount());
            orderDao.delete(order);
            JdbcUtils.endAffair(conn);
        }catch (Exception e){
            JdbcUtils.rollBack(conn);
            e.printStackTrace();
        }
        return 0;
    }
}
