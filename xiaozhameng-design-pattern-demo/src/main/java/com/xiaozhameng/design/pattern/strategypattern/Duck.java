package com.xiaozhameng.design.pattern.strategypattern;

import com.xiaozhameng.design.pattern.strategypattern.pure.FlyBehavior;
import com.xiaozhameng.design.pattern.strategypattern.pure.QuackBehavior;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <p>Description: </p>
 * <p>Copyright: @2017</p>
 * <p>Company: xiaozhameng</p>
 *
 * @author xiaozhameng
 * @version V1.0 2018/2/11
 */
public abstract class Duck {

    /**
     * 飞行行为
     */
    FlyBehavior flyBehavior;

    /**
     * 鸭子叫的行为
     */
    QuackBehavior quackBehavior;

    /**
     * 鸭子飞
     */
    public void performQuack(){
        quackBehavior.quack();
    }

    /**
     * 鸭子叫
     */
    public void performFly(){
        flyBehavior.fly();
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
