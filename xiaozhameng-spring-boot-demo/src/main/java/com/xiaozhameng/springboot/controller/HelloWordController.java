package com.xiaozhameng.springboot.controller;

import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: 第一个程序</p>
 * <p>Copyright: @2018</p>
 *
 * @author xiaozhameng
 * @version V1.0 2018/5/27
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("Hello")
public class HelloWordController {

    private static final Logger logger = LoggerFactory.getLogger(HelloWordController.class);

    @RequestMapping("/sayHello")
    @ResponseBody
    public String helloWord(){
        return "hello Spring-boot";
    }

    @RequestMapping("/threadMultyTest")
    @ResponseBody
    public String threadMultyTest(HttpServletRequest request){
        String reDataName = request.getParameter("reDataName");
        logger.info("有测试请求线程，reDataName:{}",reDataName);
        String returnResult = "";
        if (Objects.equal("1001",reDataName)){
            logger.info("1001 请求，你可以先睡30秒----------");
            try {
                TimeUnit.SECONDS.sleep(30);
                returnResult =  "不好意思，路上堵车了，我是" + reDataName;
            } catch (InterruptedException e) {
                logger.error("1001 请求请求发生异常:",e);
            }
        }else {
            try {
                TimeUnit.SECONDS.sleep(1);
                returnResult =  reDataName;
            } catch (InterruptedException e) {
                logger.error("请求线程被其他线程中断了");
            }
        }
        return returnResult;
    }

}
