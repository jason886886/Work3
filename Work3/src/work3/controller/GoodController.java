package work3.controller;

import work3.domain.Good;
import work3.domain.Order;
import work3.service.GoodServiceImpl;
import work3.service.OrderServiceImpl;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author jason
 */
public class GoodController {

    private final GoodServiceImpl goodService = new GoodServiceImpl();
    private final OrderServiceImpl orderService = new OrderServiceImpl();

    /**
     * 查看所有商品
     */
    public void showAll() {
        while (true) {
            System.out.println("1: 价格升序");
            System.out.println("2: 价格降序");
            System.out.println("3: 编号升序");
            System.out.println("4: 编号降序");
            System.out.print("请选择排序方式:");
            Scanner sc = new Scanner(System.in);
            int selectWay = sc.nextInt();
            boolean isShow = true;
            switch (selectWay) {
                case 1:
                    selectAll("good_price");
                    break;
                case 2:
                    selectAll("good_price desc");
                    break;
                case 3:
                    selectAll("good_id");
                    break;
                case 4:
                    selectAll("good_id desc");
                    break;
                default:
                    System.out.println("非法输入, 请重试");
                    isShow = false;
            }
            if (isShow) {
                break;
            }
        }
    }


    /**
     * 以特定排序方式查看所有商品
     * @param orderBy 排序方式
     */
    public void selectAll(String orderBy) {
        ArrayList<Good> list = goodService.selectAllGoods(orderBy);
        for (Good good : list) {
            System.out.println(good);
            System.out.println();
        }
    }

    /**
     * 新增商品
     */
    public void insertGood() {
        while (true) {
            System.out.println("请输入商品信息:");
            Scanner sc = new Scanner(System.in);
            System.out.print("请输入商品名称: ");
            String name = sc.next();
            System.out.print("请输入商品定价: ");
            double price = sc.nextDouble();
            System.out.print("请输入进货量: ");
            int stock = sc.nextInt();
            int isInsert = goodService.insertGood(new Good(name, price, stock));
            if (isInsert != 0) {
                System.out.println("商品已添加");
                break;
            } else {
                System.out.println("添加失败, 请重试");
            }
        }
    }

    /**
     * 商品进货
     */
    public void replenishStock() {
        Scanner sc = new Scanner(System.in);
        Good good;
        int isSuccess;
        do {
            System.out.print("请输入需进货商品的编号: ");
            int id = sc.nextInt();
            good = goodService.selectGoodById(id);
        } while (good == null);
        do {
            System.out.print("请输入进货量: ");
            int stock = sc.nextInt();
            isSuccess = goodService.replenishStock(good, stock);
        } while (isSuccess == 0);
        System.out.println("进货成功");
    }

    /**
     * 更改商品定价
     */
    public void changeGoodPrice() {
        Scanner sc = new Scanner(System.in);
        Good good;
        int isSuccess;
        do {
            System.out.print("请输入需更改价格商品的编号: ");
            int id = sc.nextInt();
            good = goodService.selectGoodById(id);
        } while (good == null);
        do {
            System.out.print("请输入商品价格: ");
            double price = sc.nextDouble();
            isSuccess = goodService.changeGoodPrice(good, price);
        } while (isSuccess == 0);
        System.out.println("更改成功");
    }

    /**
     * 下架商品
     */
    public void deleteGood() {
        Scanner sc = new Scanner(System.in);
        Good good;
        int isSuccess, id;
        boolean isOrder = false;
        do {
            System.out.print("请输入需下架商品的编号: ");
            id = sc.nextInt();
            good = goodService.selectGoodById(id);
        } while (good == null);
        ArrayList<Order> list = orderService.selectAllOrders("order_id");
        for (Order order : list) {
            if (order.getGoodId() == id) {
                isOrder = true;
                System.out.println(order);
                orderService.deleteOrder(order);
            }
        }
        isSuccess = goodService.deleteGood(good);
        if (isOrder) {
            System.out.println("以上订单中含有已下架商品, 已自动取消订单");

        }
        if (isSuccess != 0) {
            System.out.println("成功下架");
        }
    }
}
