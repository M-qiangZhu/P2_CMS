package com.atguigu.cms.view;

import com.atguigu.cms.domain.Customer;
import com.atguigu.cms.service.CustomerList;


/**
 * 连接器：连接用户和管理器，用户的操作最终会转化为管理器的方法调用
 */
public class CustomerView {

    /**
     * CustomerView为主模块，负责菜单的显示和处理用户操作
     * 本类封装以下信息：
     * CustomerList custList = new CustomerList(10);
     * 	创建最大包含10客户对象的CustomerList 对象，供以下各成员方法使用。
     * 该类至少提供以下方法：
     * public void enterMainMenu()
     * private void addNewCustomer()
     * private void modifyCustomer()
     * private void deleteCustomer()
     * private void listAllCustomers()
     */

    // 关联管理器对象，它内部最多管理10个客户
    private CustomerList custList = new CustomerList(10);

    /**
     * 进入主菜单，即进入程序，它是程序真正的入口
     */
    public void enterMainMenu() {
        // 这里必须死循环
        // 声明控制循环的布尔变量
        boolean loopFalg = true;
        // 进入循环
        do {
            // 打印主菜单
            System.out.print("--------------------------客户信息管理软件----------------------------\n" +
                    "\n" +
                    "                          1 添 加 客 户\n" +
                    "                          2 修 改 客 户\n" +
                    "                          3 删 除 客 户\n" +
                    "                          4 客 户 列 表\n" +
                    "                          5 退      出\n" +
                    "\n" +
                    "                           请选择(1-5)：");

            // 通过工具类方法，从键盘获取用户输入'1'~'5'
            char choose = CMUtility.readMenuSelection();
            switch (choose) {
                case '1' :
                    addNewCustomer();
                    break;
                case '2' :
                    modifyCustomer();
                    break;
                case '3' :
                    deleteCustomer();
                    break;
                case '4' :
                    listAllCustomers();
                    break;
                case '5' :
                    loopFalg = false;
                    break;
            }

        } while (loopFalg);

    }

    /**
     * 添加新客户
     */
    private void addNewCustomer() {
        // 采集数据
        System.out.println("---------------------添加客户---------------------");
        System.out.print("姓名：");
        // 调用工具类的readString()方法，指定长度为5；此方法返回字符串，声明字符串变量接收它
        String name = CMUtility.readString(5);

        System.out.print("性别：");
        // 调用工具类的CMUtility.readChar()方法；此方法返回字符，声明字符变量接收它
        char gender = CMUtility.readChar();

        System.out.print("年龄：");
        // 调用工具类的CMUtility.readInt()方法；此方法返回一个整数，声明int型变量接收它
        int age = CMUtility.readInt();

        System.out.print("电话：");
        // 调用工具类的CMUtility.readString()方法，指定长度为15；此方法返回字符串，声明字符串变量接收它
        String phone = CMUtility.readString(15);

        System.out.print("邮箱：");
        // 调用工具类的CMUtility.readString()方法，指定长度为30；此方法返回字符串，声明字符串变量接收它
        String email = CMUtility.readString(30);

        // 创建客户对象
        // 把几个数据整合成一个客户对象
        Customer cust = new Customer(name, gender, age, phone, email);

        // 真正的添加必须通过 "管理器" 对象添加
        boolean flag = this.custList.addCustomer(cust);
        if (flag) {
            System.out.println("---------------------添加完成---------------------");
        } else {
            System.out.println("---------------------添加失败, 容量已满---------------------");
        }

    }


    /**
     * 修改客户
     */
    private void modifyCustomer() {
        System.out.println("---------------------修改客户---------------------");
        listAllCustomers();
        System.out.print("请选择待修改客户编号(-1退出) : ");
        // 使用工具类的方法readInt() 可以获取一个整数, 用变量接收
        int no = CMUtility.readInt();
        // 如果用户输入的整数是-1, 方法提前return
        if (no == -1) {
            return;
        }
        // 根据编号从 "管理器" 中获取要修改的目标对象
        Customer target = custList.getCustomer(no - 1);
        // 如果获取到的目标对象为null
        if (target == null) {
            System.out.println("编号错误, 要修改的客户不存在");
            return;
        }
        System.out.println("<直接回车表示不修改>");

        System.out.print("姓名(" + target.getName() + ") : ");
        //真的获取新名字 :
        String name = CMUtility.readString(5, target.getName());
        // 无条件设置对象的name
        target.setName(name);

        System.out.print("性别(" + target.getGender() + ") : ");
        // 获取用户输入的性别
        char gender = CMUtility.readChar(target.getGender());
        // 无条件设置对象的gender
        target.setGender(gender);

        System.out.print("年龄(" + target.getAge() +") : ");
        // 获取用户输入的年龄
        int age = CMUtility.readInt(target.getAge());
        // 无条件设置对象的age
        target.setAge(age);

        System.out.print("电话(" + target.getPhone() + ") : ");
        // 获取用户输入的电话
        String phone = CMUtility.readString(20, target.getPhone());
        // 无条件设置对象的phone
        target.setPhone(phone);

        System.out.print("邮箱(" + target.getEmail() + ") : ");
        // 获取用户输入的邮箱
        String email = CMUtility.readString(50, target.getEmail());
        // 无条件设置对象的email
        target.setEmail(email);
        System.out.println("---------------------修改完成---------------------");
        listAllCustomers();

    }

    /**
     * 删除客户
     */
    private void deleteCustomer() {
        System.out.println("---------------------删除客户---------------------");
        System.out.print("请选择待删除客户编号(-1退出)：");
        // 获取编号
        int num = CMUtility.readInt();
        if (num == -1) {
            return;
        }
        System.out.print("确认是否删除(Y/N)：");
        char confirm = CMUtility.readConfirmSelection();
        if (confirm == 'Y') {
            // 用管理器删除
            boolean flag = custList.deleteCustomer(num - 1);
            if (flag) {
                System.out.println("---------------------删除完成---------------------");
            } else {
                System.out.println("----------------删除失败,编号不存在！!---------------");
            }
        }
    }

    /**
     * 列出所有客户
     */
    private void listAllCustomers() {
        System.out.println("---------------------------客户列表---------------------------");
        System.out.println("编号\t\t\t姓名\t\t\t性别\t\t\t年龄\t\t电话\t\t\t\t\t邮箱");
        Customer[] allCust = custList.getAllCustomers();
        for (int i = 0; i < allCust.length; i++) {
            System.out.println(i + 1 + "\t\t\t" + allCust[i].say());
        }
        System.out.println("-------------------------客户列表完成-------------------------\n");



    }


}
