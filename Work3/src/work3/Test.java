package work3;


import work3.dao.OrderDaoImpl;
import work3.domain.Order;
import work3.utils.JdbcUtils;

import java.sql.*;


/**
 * @author jason
 */
public class Test {

    public static void main(String[] args) {
        Connection conn = JdbcUtils.getConnection();
        OrderDaoImpl orderDao = new OrderDaoImpl(conn);
        Order order = new Order(1, 3, 4.5);
        orderDao.insert(order);

    }
}


