package work3.service;

import work3.domain.Good;

import java.util.ArrayList;

/**
 * @author jason
 */
public interface GoodService {

    /**
     * 从数据库查询整个商品表
     * @param orderBy 排序方式
     * @return 返回查询到的商品列表
     */
    ArrayList<Good> selectAllGoods(String orderBy);

    /**
     * 根据商品编号查询商品
     * @param id 商品编号
     * @return 返回查询到的商品对象
     */
    Good selectGoodById(int id);

    /**
     * 用于补充商品库存
     * @param good 要补充库存的商品对象
     * @param count 补充的商品量
     * @return 返回受影响的行数
     */
    int replenishStock(Good good, int count);

    /**
     * 更改商品价格
     * @param good 要修改的商品对象
     * @param price 更改后的价格
     * @return 返回受影响的行数
     */
    int changeGoodPrice(Good good, double price);
    /**
     * 新增商品
     * @param good 要加入的商品对象
     * @return 返回受影响行数
     */
    int insertGood(Good good);

    /**
     * 下架商品
     * @param good 传入要删除的商品对象
     * @return 返回受影响的行数
     */
    int deleteGood(Good good);

    /**
     * 卖出商品
     * @param good 要卖出的商品对象
     * @param count 卖出数量
     * @return 返回受影响行数
     */
    int saleGood(Good good, int count);


}
