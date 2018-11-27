package com.xiaozhameng.design.pattern.strategypattern;

/**
 * <p>Description: </p>
 * <p>Copyright: @2017</p>
 * <p>Company: xiaozhameng</p>
 *
 * @author xiaozhameng
 * @version V1.0 2018/2/13
 */
public class StrategyTest {

    public static void main(String[] args) {
        Duck model = new MallarDuck();
        model.performQuack();
        model.performFly();
    }

}
