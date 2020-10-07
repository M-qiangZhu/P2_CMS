package com.atguigu.cms.service;

import com.atguigu.cms.domain.Customer;

/**
 * 管理模块 : 负责管理所有客户对象，内部使用数组管理
 * 不是javabean，彻底封装
 */

public class CustomerList {

    // 对象关联 : 关联一个引用数组对象
    private Customer[] customers; // 用名为customersd的数组来保存客户对象
    private int realCount = 0; // 记录以保存的对象数据


    /**
     *  用途：构造器，用来初始化customers数组
     参数：totalCount：指定customers数组的最大空间
     */
    // 在构造器中创建数组对象
    public CustomerList(int totalCount) { //
        this.customers = new Customer[totalCount]; // 真正创建数组对象
    }

    /**
     * public boolean addCustomer(Customer customer)
     * 用途：将参数customer添加组中最后一个客户对象记录之后
     * 参数：customer指定要添加的客户对象
     * 返回：添加成功返回true；false表示数组已满，无法添加
     */
    public boolean addCustomer(Customer customer) {
        // 代码 每调用一次，都希望参数中的对象自动插入到数组中
        if (realCount == customers.length) {
            grow();
            // return false;
        }
        // 记录当前传入参数
        this.customers[realCount] = customer;
        // 调整计数器
        realCount++;
        return true;
    }

    private void grow() {
        System.out.println("要扩容了");
        // 1)
        Customer[] newArr = new Customer[customers.length * 3 / 2];
        // 2)
        for (int i = 0; i < customers.length; i++) {
            newArr[i] = customers[i];
        }
        // 3)
        customers = newArr;
    }

    /**
     * public Customer[] getAllCustomers()
     * 用途：返回数组中记录的所有客户对象
     * 返回： Customer[] 数组中包含了当前所有客户对象，该数组长度与对象个数相同。
     * @return : 返回的是完美数组
     */
    public Customer[] getAllCustomers() {
        // 1) 创建新数组，容量是计数器
        Customer[] newCustomers = new Customer[realCount];
        // 2) 依次把内部数组的元素复制到新的数组中
        for (int i = 0; i < realCount; i++) { // 长度为realCount
            newCustomers[i] = this.customers[i]; // this.customers
        }
        // 3) 返回新数组
        return newCustomers;
    }

    /**
     * getCustomer(int index)
     * 用途：返回参数index指定索引位置的客户对象记录
     * 参数： index指定所要获取的客户对象在数组中的索引位置
     * 返回：封装了客户信息的Customer对象
     * @return
     */
    public Customer getCustomer(int index) {
        if (index < 0 || index >= realCount) { // 不在范围内
            return null;
        }
        return this.customers[index];
    }


    /**
     * public boolean deleteCustomer(int index)
     * 用途：从数组中删除参数index指定索引位置的客户对象记录
     * 参数： index指定所删除对象在数组中的索引位置
     * 返回：删除成功返回true；false表示索引无效，无法删除
     *
     */
    public boolean deleteCustomer(int index) {
        if(index >= realCount) { // 索引无效
            return false;
        } else {
            // 1) 把要删除的下标位置处置为空洞
            this.customers[index] = null;
            // 2) 依次把右面所有有效对象左移
            for (int i = index; i < realCount - 1; i++) {
                this.customers[i] = this.customers[i + 1];
            }
            // 3) 把之前的最后一个有效元素位置处置空
            this.customers[realCount - 1] = null;
            // 4) 调整计数器
            realCount--;
            return true;
        }
    }


}
