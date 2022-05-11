package com.atguigu.admin;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest   //若想使用容器中的test功能则需要加上SpringBootTest注解
@Slf4j
//给该测试类起名
@DisplayName("Junit5功能测试类")
public class Junit5Test {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //测试前置条件
    @DisplayName("测试前置条件")
    @Test
    void testAssumptions() {
        Assumptions.assumeTrue(false, "结果不对"); //这个结果也和 disable注解一样 测试的结果是跳过

        //下面不会执行
        System.out.println(11111);
    }

    @Test
    //给该测试方法起名
    @DisplayName("测试displayname注解")
    void testDisplayName() {
        System.out.println("hello world!");
        System.out.println("jdbcTemplate = " + jdbcTemplate);


    }

    @Disabled //禁用此测试方法
    @Test
    @DisplayName("测试方法二")
    void test2() {
        System.out.println("world hello!");
    }

    @DisplayName("测试简单断言")
    @Test
    void testSimpleAssertions() {
        int cal = cal(2, 3);
        Assertions.assertEquals(5, cal, "业务逻辑运算失败"); //断言  cal 是否等于 5
        Object obj = new Object();
        Object obj1 = new Object();
        Assertions.assertSame(obj, obj1, "这两个对象不相同");

    }

    @Test
    @DisplayName("array assertion")
    public void array() {
        //判断两个对象或原始类型的数组是否相等
        assertArrayEquals(new int[]{1, 2}, new int[]{1, 2}, "不一样捏!");
    }

    @Test
    @DisplayName("异常测试")
    public void exceptionTest() {
        //断定是否异常  异常不报错,正常反而报错
        assertThrows(ArithmeticException.class, () -> {
            int i = 10 / 2;
        }, "数学看来不是体育老师教的!");
    }

    @Test
    @DisplayName("超时测试")
    public void timeoutTest() {
        //如果测试方法时间超过1s将会异常
        //断言一下测试不会超过1s
        Assertions.assertTimeout(Duration.ofMillis(1000), () -> Thread.sleep(1500), "超时了咧！");
    }

    @Test
    @DisplayName("组合断言")
    void all() {
        //所有断言全部成功才会往下走
        assertAll("test",
                () -> assertTrue(true && true),
                () -> assertEquals(2, 1),
                () -> assertArrayEquals(new int[]{2, 2}, new int[]{1, 2}, "不一样捏!")

        );

    }

    //        通过 fail 方法直接使得测试失败
    @Test
    @DisplayName("fail")
    public void shouldFail() {
        if (1 == 2) {
            fail("This should fail");
        }

    }


    int cal(int i, int j) {

        return i + j;

    }

    //在每一次测试之前都会执行
    @BeforeEach
    void testBeforeEach() {
        System.out.println("测试就要开始了!");
    }

    @RepeatedTest(5)  //该测试方法重复执行5次
    @Test
    void test3() {
        System.out.println(5);
    }

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
        // 当当前方法执行超过500毫秒就算超时,超时则报超时异常
    void testTimeOut() {
//            Thread.sleep(600);


    }

    //在每一次测试之后都会执行
    @AfterEach
    void testAfterEach() {
        System.out.println("测试已经结束了!");
    }

    //以下的两种方法必须为静态方法
    @BeforeAll
    static void testBeforeAll() {
        System.out.println("所有测试就要开始了!");
    }

    @AfterAll
    static void testAfterAll() {
        System.out.println("所有测试已经结束了!");
    }


}
