package com.xiaozhameng.design.pattern.observerpattern.myown;

/**
 * <p>Description: 观察者接口</p>
 * <p>Copyright: @2018</p>
 * <p>Company: YeePay</p>
 *
 * @author xiaozhameng
 * @version V1.0 2018/3/10
 */
public interface Observer {

    /**
     * 更新的数据
     */
    void update(float temperature,float humidity,float pressure);

}
