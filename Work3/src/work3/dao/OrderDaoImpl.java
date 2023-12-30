package work3.dao;

import work3.domain.Order;
import work3.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author jason
 */
public class OrderDaoImpl implements OrderDao {
    private final String id = "`order_id`";
    private final String goodId = "`good_id`";
    private final String price = "`order_price`";
    private final String count = "`order_count`";
    private final String createTime = "`create_time`";

    private final Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    private final String columns = String.format("%s,%s,%s,%s,%s", id, goodId, count, price, createTime);
    private final String changeAbleColumns = String.format("%s,%s,%s", goodId, count, price);

    public OrderDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public ArrayList<Order> selectAll(String orderBy) {
        ArrayList<Order> orderList = new ArrayList<>();
        try {
            String sql = String.format("select %s from `order` order by ?", columns);
            rs = JdbcUtils.select(conn, stmt, sql, orderBy);
            while (rs.next()) {
                String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp(createTime));
                orderList.add(new Order(rs.getInt(id), rs.getInt(goodId), rs.getInt(count), rs.getDouble(price), dateString));
            }
            return orderList;
        } catch (SQLException e) {
            throw new DataNotFoundException("未查询到数据, 请校验查询条件合法性");
        } finally {
            JdbcUtils.release(null, stmt, rs);
        }
    }

    @Override
    public Order selectById(int oderId) {
        try {
            String sql = String.format("select %s from `oder` where %s = ?", columns, id);
            rs = JdbcUtils.select(conn, stmt, sql, oderId);
            rs.next();
            String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp(createTime));
            return new Order(rs.getInt(id), rs.getInt(goodId), rs.getInt(count), rs.getDouble(price), dateString);
        } catch (Exception e) {
            throw new DataNotFoundException("未查询到数据, 请校验查询条件合法性");
        } finally {
            JdbcUtils.release(null, stmt, rs);
        }
    }

    @Override
    public int update(Order order) {
        try {
            String sql = String.format("update `order` set %s = ?, set %s = ? where %s = ?", count, price, id);
            return JdbcUtils.update(conn, stmt, sql, order.getCount(), order.getOderPrice(), order.getOderId());
        } catch (Exception e) {
            throw new DataNotFoundException("非法更新, 请校验数据合法性");
        } finally {
            JdbcUtils.release(stmt, rs);
        }
    }

    @Override
    public int insert(Order order) {
        try {
            String sql = String.format("insert into `order` (%s) values (?, ?, ?)", changeAbleColumns);
            return JdbcUtils.update(conn, stmt, sql, order.getGoodId(), order.getCount(), order.getOderPrice());
        } catch (Exception e) {
            throw new CanNotUpdateException("非法插入, 请校验数据合法性");
        }finally {
            JdbcUtils.release(stmt, rs);
        }
    }


    @Override
    public int delete(Order order) {
        try{
            String sql = String.format("delete from order where %s = ?", id);
            return JdbcUtils.update(conn, stmt, sql, order.getOderId());
        }catch (Exception e){
            throw new CanNotUpdateException("非法删除, 请校验数据合法性");
        }finally {
            JdbcUtils.release(stmt, rs);
        }
    }
}
