package com.atguigu.admin;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("嵌套测试")
@SpringBootTest
public class TestingAStackDemo {

        Stack<Object> stack;

        @ParameterizedTest   //参数化测试
        @DisplayName("参数化测试")
        @ValueSource(ints={1,2,3,4,5})
        void testParameterized(int i){
            System.out.println(i);
        }
        @ParameterizedTest
        @DisplayName("参数化测试")
        @MethodSource("stringStream")
        void testParameterized2(String str){
            System.out.println("str = " + str);
        }
        static Stream<String> stringStream(){
            return Stream.of("我是练习时长两年半的个人练习生:蔡徐坤","我喜欢唱跳Rap篮球");
        }

        @Test
        @DisplayName("is instantiated with new Stack()")
        void isInstantiatedWithNew() {
            new Stack<>();
            //嵌套测试情况下，外层的Test不能驱动beforeeach 和 beforeAll 以及after的种种注解方法
            assertNull(stack,"栈为空");
        }

        @Nested
        @DisplayName("when new")
        class WhenNew {

            @BeforeEach
            void createNewStack() {
                stack = new Stack<>();

            }

            @Test
            @DisplayName("is empty")
            void isEmpty() {
                assertTrue(stack.isEmpty());
            }

            @Test
            @DisplayName("throws EmptyStackException when popped")
            void throwsExceptionWhenPopped() {
                assertThrows(EmptyStackException.class, stack::pop);
            }

            @Test
            @DisplayName("throws EmptyStackException when peeked")
            void throwsExceptionWhenPeeked() {
                assertThrows(EmptyStackException.class, stack::peek);
            }

            @Nested
            @DisplayName("after pushing an element")
            class AfterPushing {

                String anElement = "an element";
                //嵌套测试情况下，内层的Test能驱动beforeEach 和 beforeAll 以及after的种种注解方法
                @BeforeEach
                void pushAnElement() {
                    stack.push(anElement);
                }

                @Test
                @DisplayName("it is no longer empty")
                void isNotEmpty() {
                    assertFalse(stack.isEmpty());
                }

                @Test
                @DisplayName("returns the element when popped and is empty")
                void returnElementWhenPopped() {
                    assertEquals(anElement, stack.pop());
                    assertTrue(stack.isEmpty());
                }

                @Test
                @DisplayName("returns the element when peeked but remains not empty")
                void returnElementWhenPeeked() {
                    assertEquals(anElement, stack.peek());
                    assertFalse(stack.isEmpty());
                }
            }
        }


}
