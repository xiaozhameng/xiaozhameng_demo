package com.xiaozhameng.log4j.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qiaofj on 2017/1/12.
 * 测试base类种使用logger对象
 */
public class BaseTest{

    protected Logger logger = LoggerFactory.getLogger(getClass());

}
