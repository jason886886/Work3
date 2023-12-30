package work3.dao;

import work3.domain.Good;
import work3.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author jason
 */
public class GoodDaoImpl implements GoodDao {

    private final String id = "good_id";
    private final String name = "good_name";
    private final String price = "good_price";
    private final String stock = "good_stock";

    private final Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    private final String columns = String.format("%s,%s,%s,%s", id, name, price, stock);
    private final String changeAbleColumns = String.format("%s,%s,%s", name, price, stock);

    public GoodDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public ArrayList<Good> selectAll(String orderBy) {
        ArrayList<Good> goodList = new ArrayList<>();
        try {
            String sql = String.format("select %s from `good` order by %s", columns, orderBy);
            rs = JdbcUtils.select(conn, stmt, sql, null);
            while (rs.next()) {
                goodList.add(new Good(rs.getInt(id), rs.getString(name), rs.getDouble(price), rs.getInt(stock)));
            }
            return goodList;
        } catch (SQLException e) {
            throw new DataNotFoundException("未查询到商品, 请校验查询条件合法性");
        } finally {
            JdbcUtils.release(stmt, rs);
        }
    }

    @Override
    public Good selectById(int goodId) {
        try {
            String sql = String.format("select %s from `good` where %s = ?", columns, id);
            rs = JdbcUtils.select(conn, stmt, sql, goodId);
            rs.next();
            return new Good(rs.getInt(id), rs.getString(name), rs.getDouble(price), rs.getInt(stock));
        } catch (Exception e) {
            throw new DataNotFoundException("未查询到商品, 请校验查询条件合法性");
        } finally {
            JdbcUtils.release(stmt, rs);
        }
    }

    @Override
    public int update(Good good) {
        try {
            String sql = String.format("update `good` set %s = ?, %s = ?, %s = ? where %s = ?", name, price, stock, id);
            return JdbcUtils.update(conn, stmt, sql, good.getGoodName(), good.getGoodPrice(), good.getGoodStock(), good.getGoodId());
        } catch (Exception e) {
            throw new CanNotUpdateException("非法更新, 请校验数据合法性");
        } finally {
            JdbcUtils.release(stmt, rs);
        }
    }

    @Override
    public int insert(Good good) {
        try {
            String sql = String.format("insert into `good` (%s) values (?, ?, ?)", changeAbleColumns);
            return JdbcUtils.update(conn, stmt, sql, good.getGoodName(), good.getGoodPrice(), good.getGoodStock());
        } catch (Exception e) {
            throw new CanNotUpdateException("非法插入, 请校验数据合法性");
        } finally {
            JdbcUtils.release(stmt, rs);
        }
    }

    @Override
    public int delete(Good good) {
        try {
            String sql = String.format("delete from `good` where %s = ?", id);
            return JdbcUtils.update(conn, stmt, sql, good.getGoodId());
        } catch (Exception e) {
            throw new CanNotUpdateException("非法删除, 请校验数据合法性");
        } finally {
            JdbcUtils.release(stmt, rs);
        }
    }
}
