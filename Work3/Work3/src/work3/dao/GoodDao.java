package work3.dao;

import work3.domain.Good;

import java.util.ArrayList;



/**
 * @author jason
 */
public interface GoodDao {
    /**
     * 从数据库查询整个商品表
     * @param orderBy 排序方式
     * @return 返回查询到的商品列表
     */
      ArrayList<Good> selectAll(String orderBy);

    /**
     * 根据商品编号查询商品
     * @param id 商品编号
     * @return  返回一个商品对象
     */
     Good selectById(int id);

    /**
     * 更新表的某一行
     * @param good 传入需要修改的商品对象
     * @return 返回受影响的行数
     */
    int update(Good good);

    /**
     * 在表中插入新的一行
     * @param good 传入要插入表的商品对象
     * @return 返回受影响的行数
     */
     int insert(Good good);

    /**
     * 删除表中的一行
     * @param good 传入要删除的商品对象
     * @return 返回受影响的行数
     */
     int delete(Good good);

}
