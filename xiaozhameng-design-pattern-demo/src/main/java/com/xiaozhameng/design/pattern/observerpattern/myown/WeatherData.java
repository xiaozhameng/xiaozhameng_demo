package com.xiaozhameng.design.pattern.observerpattern.myown;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: </p>
 * <p>Copyright: @2018</p>
 * <p>Company: YeePay</p>
 *
 * @author xiaozhameng
 * @version V1.0 2018/3/10
 */
public class WeatherData implements Subject{

    private List<Observer> observerList;

    private float temperature;
    private float humdity;
    private float pressure;

    public WeatherData(){
        observerList = new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer observer) {
        if (observer != null){
            observerList.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer :observerList){
            if (observer != null){
                observer.update(temperature,humdity,pressure);
            }
        }
    }

    /**
     * 数据更新
     */
    public void setUpdate(float temperature,float humdity,float pressure){
        this.temperature = temperature;
        this.humdity = humdity;
        this.pressure = pressure;
        notifyObservers();
    }
}
