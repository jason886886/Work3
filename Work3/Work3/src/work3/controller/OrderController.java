package work3.controller;

import work3.domain.Good;
import work3.domain.Order;
import work3.service.GoodService;
import work3.service.GoodServiceImpl;
import work3.service.OrderServiceImpl;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author jason
 */
public class OrderController {
    private final OrderServiceImpl orderService = new OrderServiceImpl();
    private final GoodService goodService = new GoodServiceImpl();

    /**
     * 查询所有订单
     */
    public void showAll(){
        while (true) {
            System.out.println("1: 价格升序");
            System.out.println("2: 价格降序");
            System.out.println("3: 下单时间升序");
            System.out.println("4: 下单时间降序");
            System.out.print("请选择排序方式:");
            Scanner sc = new Scanner(System.in);
            int selectWay = sc.nextInt();
            boolean isShow = true;
            switch (selectWay) {
                case 1:
                    selectAll("order_price");
                    break;
                case 2:
                    selectAll("order_price desc");
                    break;
                case 3:
                    selectAll("order_id");
                    break;
                case 4:
                    selectAll("order_id desc");
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
     * 以特定排序方式查看订单
     * @param orderBy 排序方式
     */
    public void selectAll(String orderBy){
        ArrayList<Order> list = orderService.selectAllOrders(orderBy);
        for (Order order : list) {
            System.out.println(order);
            System.out.println();
        }
    }

    /**
     * 创建订单
     */
    public void createOrder(){
        Scanner sc = new Scanner(System.in);
        Good good;
        int id, isSuccess;
        do{
            System.out.print("请输入您想购买商品的编号: ");
            id = sc.nextInt();
            good = goodService.selectGoodById(id);
        }while(good == null);
        do{
            System.out.print("请输入您想购买的数量: ");
            int count = sc.nextInt();
            double price = count * good.getGoodPrice();
            Order order = new Order(id, count, price);
            isSuccess = orderService.insertOrder(order);
        }while(isSuccess == 0);
        System.out.println("已下单");
    }

    /**
     * 变更订单购买数量
     */
    public void changeOrderCount(){
        Scanner sc = new Scanner(System.in);
        int isSuccess;
        Order order;
        do{
            System.out.print("请输入要更改的订单编号: ");
            int id = sc.nextInt();
            order = orderService.selectOrderById(id);
        }while(order == null);
        System.out.println(order);
        do{
            System.out.print("您想将购买数量更改为: ");
            int count = sc.nextInt();
            isSuccess = orderService.changeOrderCount(order, count);
        }while(isSuccess == 0);
        System.out.println("已变更购买数量");
    }

    /**
     *  取消订单
     */
    public void deleteOrder(){
        Scanner sc = new Scanner(System.in);
        Order order;
        do{
            System.out.print("请输入您想删除订单的编号: ");
            int id = sc.nextInt();
            order = orderService.selectOrderById(id);
        }while(order == null);
        orderService.deleteOrder(order);
        System.out.println("已取消订单");
    }
}
