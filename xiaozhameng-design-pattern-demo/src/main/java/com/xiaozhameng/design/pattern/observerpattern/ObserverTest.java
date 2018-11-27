package com.xiaozhameng.design.pattern.observerpattern;

import com.xiaozhameng.design.pattern.observerpattern.myown.CurrentConditionsDisplay;
import com.xiaozhameng.design.pattern.observerpattern.myown.WeatherData;
import org.junit.Test;

/**
 * <p>Description: </p>
 * <p>Copyright: @2018</p>
 * <p>Company: YeePay</p>
 *
 * @author xiaozhameng
 * @version V1.0 2018/3/10
 */
public class ObserverTest {

    @Test
    public void testMyOwen(){
        // 测试项：让具体主题发生变化
        WeatherData data = new WeatherData();
        // 观察者：并注册
        CurrentConditionsDisplay test = new CurrentConditionsDisplay(data);

        data.setUpdate(22.58F,12F,20F);
    }

}
