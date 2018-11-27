package com.xiaozhameng.design.pattern.observerpattern.myown;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: </p>
 * <p>Copyright: @2018</p>
 * <p>Company: YeePay</p>
 *
 * @author xiaozhameng
 * @version V1.0 2018/3/10
 */
public class CurrentConditionsDisplay implements Observer{

    private static final Logger logger = LoggerFactory.getLogger(CurrentConditionsDisplay.class);

    private float tempereture ;
    private float humidity;
    private Subject subject;

    public CurrentConditionsDisplay(Subject subject){
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.tempereture = temperature;
        this.humidity = humidity;
        logger.info("CurrentConditionsDisplay 监听到被观察的主题发生了变化{},{}：",temperature,humidity);
    }
}

