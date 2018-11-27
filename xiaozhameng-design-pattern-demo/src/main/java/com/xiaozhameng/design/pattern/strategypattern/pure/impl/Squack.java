package com.xiaozhameng.design.pattern.strategypattern.pure.impl;

import com.xiaozhameng.design.pattern.strategypattern.pure.QuackBehavior;

/**
 * <p>Description: </p>
 * <p>Copyright: @2017</p>
 * <p>Company: xiaozhameng</p>
 *
 * @author xiaozhameng
 * @version V1.0 2018/2/11
 */
public class Squack  implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("----不是真实的鸭子，不能叫----");
    }
}
