package com.xiaozhameng.design.pattern.decoratorpattern;

/**
 * <p>Description: </p>
 * <p>Copyright: @2018</p>
 * <p>Company: YeePay</p>
 *
 * @author fengjun.qiao
 * @version V1.0 2018/5/27
 */
public class Test {

    /**
     *  获取摩卡对象：先要有一个饮料对象
     */
    @org.junit.Test
    public void testDecorator(){
        // 先创建一个饮料对象
        Beverage espresso = new Espresso();
        System.out.println("espresso 花费："+espresso.cost());

        Beverage beverage = new Mocha(espresso);
        System.out.println("Mocha 花费："+ beverage.cost());
        System.out.println("Mocha 描述："+ beverage.getDescription());
    }
}
