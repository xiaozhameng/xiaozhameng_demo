package com.xiaozhameng.design.pattern.decoratorpattern;

/**
 * <p>Description: </p>
 * <p>Copyright: @2018</p>
 * <p>Company: YeePay</p>
 *
 * @author fengjun.qiao
 * @version V1.0 2018/5/27
 */
public abstract class Beverage {

    String description = "Unknown Beverage";

    public abstract double cost();

    public String getDescription(){
        return description;
    }
}
