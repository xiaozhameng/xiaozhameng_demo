package com.xiaozhameng.design.pattern.observerpattern.myown;

/**
 * <p>Description: 主题接口</p>
 * <p>Copyright: @2018</p>
 * <p>Company: YeePay</p>
 *
 * @author xiaozhameng
 * @version V1.0 2018/3/10
 */
public interface Subject {

    /**
     * 注册成为该主题的观察者
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 从观察中移除
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知所有的观察者
     */
    void notifyObservers();

}
