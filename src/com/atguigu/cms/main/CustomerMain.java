package com.atguigu.cms.main;

import com.atguigu.cms.domain.Customer;
import com.atguigu.cms.service.CustomerList;
import com.atguigu.cms.view.CustomerView;

public class CustomerMain {

    public static void main(String[] args) {

        CustomerView customerView = new CustomerView();
        customerView.enterMainMenu();

    }








    // 测试main
    public static void main2(String[] args) {
        System.out.println("-------------------------------普通测试-----------------------------------");
        Customer cust1 = new Customer("大佬", '男', 10, "12345678910", "zhang1@qq.com");
        Customer cust2 = new Customer("二佬", '女', 20, "12345678910", "zhang2@qq.com");
        Customer cust3 = new Customer("三佬", '男', 30, "12345678910", "zhang3@qq.com");
        Customer cust4 = new Customer("四佬", '女', 40, "12345678910", "zhang4@qq.com");
        Customer cust5 = new Customer("五佬", '男', 50, "12345678910", "zhang5@qq.com");
        Customer cust6 = new Customer("六佬", '女', 60, "12345678910", "zhang6@qq.com");
        Customer cust7 = new Customer("七佬", '男', 70, "12345678910", "zhang7@qq.com");
        Customer cust8 = new Customer("八佬", '女', 80, "12345678910", "zhang8@qq.com");
        System.out.println(cust1.say());
        System.out.println(cust2.say());
        System.out.println(cust3.say());
        System.out.println(cust4.say());
        System.out.println(cust5.say());
        System.out.println(cust6.say());
        System.out.println(cust7.say());
        System.out.println(cust8.say());

        System.out.println("---------------------------测试CustomerList------------------------------");
        // 新建一个内部元素类型为Customer的customerList数组，参数为数组长度
        CustomerList custList = new CustomerList(8);
        // 向数组中添加customer对象
        boolean b1= custList.addCustomer(cust1);
        boolean b2 = custList.addCustomer(cust2);
        boolean b3 = custList.addCustomer(cust3);
        boolean b4 = custList.addCustomer(cust4);
        boolean b5 = custList.addCustomer(cust5);

        // 测试插入是否成功
        System.out.println("---------------------------测试插入是否成功------------------------------");
        System.out.println("b1 = " + b1);
        System.out.println("b2 = " + b2);
        System.out.println("b3 = " + b3);
        System.out.println("b4 = " + b4);
        System.out.println("b5 = " + b5);

        /*
        // 用coutomers数组接收customerList中的cuetomer类型的对象
        System.out.println("---------------------------测试getCustomers------------------------------");
        Customer[] customers = custList.getCustomers(); // 获取管理器内部数组，内部可能有空洞，存在null值元素
        for (int i = 0; i < customers.length; i++) {
             // if (customers[i] != null) {
            System.out.println(customers[i]);
            // }
        }
        System.out.println();
        */

        // 完美数组allCustomers
        System.out.println("---------------------------测试完美数组------------------------------");
        Customer[] allCustomers = custList.getAllCustomers();
        for (int i = 0; i < allCustomers.length; i++) {
            System.out.println(allCustomers[i].say());
        }
        System.out.println();

        // 索引对象
        System.out.println("---------------------------索引------------------------------");
        Customer cust = custList.getCustomer(200); // 获取索引为50的对象
        if (cust != null) {
            System.out.println(cust.say());
        } else {
            System.out.println("没有找到对象");
        }

        // 删除对象
        System.out.println("--------------------------删除对象------------------------------");
        boolean d1 = custList.deleteCustomer(1);
        boolean d2 = custList.deleteCustomer(2);
        boolean d8 = custList.deleteCustomer(8);

        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d8);

        Customer[] allCustomers2 = custList.getAllCustomers();
        for (int i = 0; i < allCustomers2.length; i++) {
            System.out.println(allCustomers2[i].say());
        }
        System.out.println();

    }
}
