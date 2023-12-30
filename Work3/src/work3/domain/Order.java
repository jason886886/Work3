package work3.domain;

import java.util.ArrayList;

/**
 * @author jason
 */
public class Order {
    private int orderId;
    private int goodId;
    private int count;
    private double orderPrice;
    private String createTime;

    public int getOderId() {
        return orderId;
    }

    public void setOderId(int oderId) {
        this.orderId = oderId;
    }


    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double oderPrice) {
        this.orderPrice = oderPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Order(int orderId, int goodId, int count, double orderPrice, String createTime) {
        this.orderId = orderId;
        this.goodId = goodId;
        this.count = count;
        this.orderPrice = orderPrice;
        this.createTime = createTime;
    }

    public Order(int goodId, int count, double orderPrice) {
        this.goodId = goodId;
        this.count = count;
        this.orderPrice = orderPrice;
    }

    @Override
    public String toString() {
        return String.format("订单编号: %d\n商品编号: %d\n订单购买量: %d\n订单价格: %f\n下单时间: %s", orderId, goodId, count, orderPrice, createTime);
    }
}
