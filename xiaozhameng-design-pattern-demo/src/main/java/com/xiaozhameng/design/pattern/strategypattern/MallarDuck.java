package com.xiaozhameng.design.pattern.strategypattern;

import com.xiaozhameng.design.pattern.strategypattern.pure.impl.FlyNoWay;
import com.xiaozhameng.design.pattern.strategypattern.pure.impl.Squack;

/**
 * <p>Description: </p>
 * <p>Copyright: @2017</p>
 * <p>Company: xiaozhameng</p>
 *
 * @author xiaozhameng
 * @version V1.0 2018/2/11
 */
public class MallarDuck extends Duck {

    /**
     * 对象实例化
     */
    public MallarDuck() {
        this.flyBehavior = new FlyNoWay();
        this.quackBehavior = new Squack();
    }


}
