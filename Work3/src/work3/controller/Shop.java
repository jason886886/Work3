package work3.controller;

import java.util.Scanner;

/**
 * @author jason
 */
public class Shop {
    private final GoodController goodController = new GoodController();
    private final OrderController orderController = new OrderController();

    public void operateGood() {
        Scanner sc = new Scanner(System.in);
        boolean isExit;
        do {
            isExit = false;
            System.out.println("商品操作界面");
            System.out.println("1: 特定排序查看商品");
            System.out.println("2: 添加商品");
            System.out.println("3: 进货");
            System.out.println("4: 更改商品定价");
            System.out.println("5: 下架商品");
            System.out.println("6: 退出");
            System.out.print("请输入您希望执行的操作:");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    goodController.showAll();
                    break;
                case 2:
                    goodController.insertGood();
                    break;
                case 3:
                    goodController.selectAll("good_id");
                    goodController.replenishStock();
                    break;
                case 4:
                    goodController.selectAll("good_id");
                    goodController.changeGoodPrice();
                    break;
                case 5:
                    goodController.selectAll("good_id");
                    goodController.deleteGood();
                    break;
                case 6:
                    isExit = true;
                    break;
                default:
                    System.out.println("非法输入, 请重试");
                    break;
            }
        } while (!isExit);
    }

    public void operateOrder() {
        Scanner sc = new Scanner(System.in);
        boolean isExit;
        do {
            isExit = false;
            System.out.println("订单操作界面");
            System.out.println("1: 特定排序查看订单");
            System.out.println("2: 下单");
            System.out.println("3: 更改订单");
            System.out.println("4: 取消订单");
            System.out.println("5: 退出");
            System.out.print("请输入您希望执行的操作: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    orderController.showAll();
                    break;
                case 2:
                    goodController.showAll();
                    orderController.createOrder();
                    break;
                case 3:
                    orderController.selectAll("order_id");
                    orderController.changeOrderCount();
                    break;
                case 4:
                    orderController.selectAll("order_id");
                    orderController.deleteOrder();
                    break;
                case 5:
                    isExit = true;
                    break;
                default:
                    System.out.println("非法输入, 请重试");
                    break;
            }
        } while (!isExit);
    }

    public void shop() {
        Scanner sc = new Scanner(System.in);
        boolean isExit;
        do {
            isExit = false;
            System.out.println("商店操作界面");
            System.out.println("1: 操作商品");
            System.out.println("2: 操作订单");
            System.out.println("3: 退出");
            System.out.print("请输入您希望执行的操作: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    operateGood();
                    break;
                case 2:
                    operateOrder();
                    break;
                case 3:
                    isExit = true;
                    break;
                default:
                    System.out.println("非法输入, 请重试");
                    break;
            }
        }while(!isExit);
    }
}
