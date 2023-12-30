package work3.domain;

import java.util.ArrayList;

/**
 * @author jason
 */
public class Good {
    private int goodId;
    private String goodName;
    private double goodPrice;
    private int goodStock;



    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }



    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public int getGoodStock() {
        return goodStock;
    }

    public void setGoodStock(int goodStock) {
        this.goodStock = goodStock;
    }

    public Good(int goodId, String goodName, double goodPrice, int goodStock) {
        this.goodId = goodId;
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.goodStock = goodStock;
    }

    public Good(String goodName, double goodPrice, int goodStock) {
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.goodStock = goodStock;
    }

    @Override
    public String toString() {
        return String.format("商品编号: %d\n商品名称: %s\n商品价格: %f\n商品库存量: %d", goodId, goodName, goodPrice, goodStock);
    }
}
