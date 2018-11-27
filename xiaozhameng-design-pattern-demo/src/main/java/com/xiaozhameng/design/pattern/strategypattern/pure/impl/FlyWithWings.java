package com.xiaozhameng.design.pattern.strategypattern.pure.impl;

import com.xiaozhameng.design.pattern.strategypattern.pure.FlyBehavior;

/**
 * <p>Description: </p>
 * <p>Copyright: @2017</p>
 * <p>Company: xiaozhameng</p>
 *
 * @author xiaozhameng
 * @version V1.0 2018/2/11
 */
public class FlyWithWings  implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("----用翅膀飞----");
    }
}
