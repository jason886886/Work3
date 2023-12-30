package work3.utils;

import work3.dao.CanNotUpdateException;
import work3.dao.DataNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author jason
 */
public class JdbcUtils {
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static {
        try {
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 建立连接
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 释放资源
     */
    public static void release(Connection conn, PreparedStatement stmt, ResultSet res) {
        if (res != null) {
            try {
                res.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void release(PreparedStatement stmt, ResultSet rs) {
        release(null, stmt, rs);
    }

    public static void release(Connection conn) {
        release(conn, null, null);
    }


    /**
     * 查询数据
     *
     * @param sql       不带查询条件的sql语句
     * @param condition 查询条件(可为空)
     * @return 返回一个ResultSet对象
     */
    public static ResultSet select(Connection conn, PreparedStatement stmt, String sql, Object condition) {
        try {
            stmt = conn.prepareStatement(sql);
            if (condition != null) {
                stmt.setObject(1, condition);
            }
             return stmt.executeQuery();
        } catch (Exception e) {
            throw new DataNotFoundException();
        }
    }


    /**
     * 可执行增, 删, 改功能
     *
     * @param sql    不带值的sql语句
     * @param values 值
     * @return 返回受影响行数
     */
    public static int update(Connection conn, PreparedStatement stmt, String sql, Object... values) {
        try {
            stmt = conn.prepareStatement(sql);
            if (values != null) {
                int i = 1;
                for (Object value : values) {
                    stmt.setObject(i, value);
                    i++;
                }
            }
            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new CanNotUpdateException();
        }
    }

    /**
     * 开启事务
     *
     * @param conn 传入连接对象
     */
    public static void startAffair(Connection conn) {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭事务
     *
     * @param conn 传入连接对象
     */
    public static void endAffair(Connection conn) {
        try {
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 事务回滚
     *
     * @param conn 传入连接对象
     */
    public static void rollBack(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
