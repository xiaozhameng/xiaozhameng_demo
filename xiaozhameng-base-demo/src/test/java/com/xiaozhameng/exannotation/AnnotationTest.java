package com.xiaozhameng.exannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Description: </p>
 * <p>Copyright: @2018</p>
 * <p>Company: YeePay</p>
 *
 * @author fengjun.qiao
 * @version V1.0 2018/6/9
 */
public class AnnotationTest {

    /**
     * Java 注解学习笔记
     * 一、Java 内置了三种标准注解，四种元注解
     * 定义在java.lang中的注解
     *
     * @Override 注解：标识当前的方法覆盖超类中的方法
     * @Deprecated 注解：若使用了他注解的元素，编译器会发出警告
     * @SuppressWarnnings：抑制编译器警告 元注解：修饰注解的注解
     *
     * @Target 用来定义你的注解要作用于什么地方，例如是一个方法或者一个域
     * @Rectetion 用来定义该注解在哪一个级别可用（保留该信息）
     * @Document 将次注解添加到javadoc 中
     * @Inherited 允许子类继承父类中的注解
     * <p>
     * 二、注解基本语法
     * 注解的使用跟几乎与修饰符的使用一模一样，例如添加到方法上的注解@Test
     * <p>
     * 三、定义注解
     * 1、注解定义与其他任何java接口定义一样，注解也会被编译成class文件
     * 2、注解中一般会包含一些元素，若没有元素定义的注解，称之为标记注解，例如@Test 注解
     *
     * 四、编写注解处理器
     * 如果没有用来读取注解的工具，注解比起注释，也就没有多大用处了。在java中使用注解，非常重要的一个部分就是创建和使用注解处理器
     * javaSE5 扩展了反射机制的API ，用以帮助程序员构造这类工具，同事，他还提供了一个外部工具apt，帮助程序员解析带有注解的java源码
     *
     * 五、注解元素
     * 注解元素可用的类型主要有
     *      所有的基本类型：int float，boolean等等
     *      String
     *      Class
     *      enum
     *      Annotation
     *      以及以上类型的数组
     *
     * 六、默认值限制：
     * 1、注解元素不能允许又不确定的值，也就是说，注解元素要么有默认值，要么在使用注解时必须提供元素的值
     * 2、
     */


    // 定义注解
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface MyTest {
        public int id();
        public String descrition() default "no description";
    }

    // 使用注解
    class AnnotationUseTest{
        @MyTest(id = 48)
        public boolean validatePwd(String pwd){
            return pwd.matches("\\w*\\d\\w*");
        }
    }



}
