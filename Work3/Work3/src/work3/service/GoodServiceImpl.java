package work3.service;

import work3.dao.GoodDaoImpl;
import work3.domain.Good;
import work3.utils.JdbcUtils;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author jason
 */
public class GoodServiceImpl implements GoodService {
    private Connection conn;
    private GoodDaoImpl goodDao;

    @Override
    public ArrayList<Good> selectAllGoods(String orderBy) {
        try {
            conn = JdbcUtils.getConnection();
            goodDao = new GoodDaoImpl(conn);
            ArrayList<Good> goodList = goodDao.selectAll(orderBy);
            JdbcUtils.release(conn);
            return goodList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtils.release(conn);
        }
    }

    @Override
    public Good selectGoodById(int id) {
        try {
            conn = JdbcUtils.getConnection();
            goodDao = new GoodDaoImpl(conn);
            return goodDao.selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtils.release(conn);
        }

    }

    @Override
    public int replenishStock(Good good, int count) {
        try {
            conn = JdbcUtils.getConnection();
            JdbcUtils.startAffair(conn);
            goodDao = new GoodDaoImpl(conn);
            if(count < 0){
                throw new IllegalCountException("非法进货数量, 请校验数据合法性");
            }
            good.setGoodStock(good.getGoodStock() + count);
            int result = goodDao.update(good);
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
    public int changeGoodPrice(Good good, double price) {
        try{
            conn = JdbcUtils.getConnection();
            JdbcUtils.startAffair(conn);
            goodDao = new GoodDaoImpl(conn);
            if(price < 0){
                throw new IllegalPriceException("非法商品价格, 请重试");
            }
            good.setGoodPrice(price);
            int result =  goodDao.update(good);
            JdbcUtils.endAffair(conn);
            return result;
        }catch (Exception e){
            JdbcUtils.rollBack(conn);
            e.printStackTrace();
            return 0;
        }finally {
            JdbcUtils.release(conn);
        }
    }

    @Override
    public int insertGood(Good good) {
        try {
            conn = JdbcUtils.getConnection();
            JdbcUtils.startAffair(conn);
            goodDao = new GoodDaoImpl(conn);
            if (good.getGoodPrice() < 0) {
                throw new IllegalPriceException("非法商品价格, 请重试");
            }
            if (good.getGoodStock() < 0) {
                throw new IllegalCountException("非法商品库存, 请重试");
            }
            int result = goodDao.insert(good);
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
    public int deleteGood(Good good) {
        try {
            conn = JdbcUtils.getConnection();
            JdbcUtils.startAffair(conn);
            goodDao = new GoodDaoImpl(conn);
            int result = goodDao.delete(good);
            JdbcUtils.endAffair(conn);
            return result;
        } catch (Exception e) {
            JdbcUtils.rollBack(conn);
            e.printStackTrace();
            return 0;
        }finally {
            JdbcUtils.release(conn);
        }
    }

    @Override
    public int saleGood(Good good, int count) {
        try {
            conn = JdbcUtils.getConnection();
            JdbcUtils.startAffair(conn);
            goodDao = new GoodDaoImpl(conn);
            if (good.getGoodPrice() < 0) {
                throw new IllegalPriceException("该商品价格非法, 无法出售, 请选择其他商品");
            }
            if (good.getGoodStock() - count < 0) {
                throw new IllegalCountException("该商品库存量不足, 请减少购买量或选择其他商品");
            }
            if(count <= 0){
                throw new IllegalCountException("非法购买数量, 请校验数据合法性");
            }
            good.setGoodStock(good.getGoodStock() - count);
            int result = goodDao.update(good);
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
}
