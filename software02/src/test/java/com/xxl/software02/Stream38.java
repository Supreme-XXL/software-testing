package com.xxl.software02;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class Stream38 {

    //    单元测试(distinct)

    //    基本测试测试
    @Test
    @ParameterizedTest
    void testDistinct01(){
        /*
         * 下界 0 1 2 个
         * 中间 6 个
         * 上界 10 11 12 个*/

//        0个
        List<String> list_0 = new ArrayList<>();
        assertEquals(0l,list_0.stream().distinct().count());

//        1个
        List<String> list_1 = new ArrayList<>();
        list_1.add("x");
        list_1.add("x");
        list_1.add("x");
        list_1.add("x");
        assertEquals(1l,list_1.stream().distinct().count());
        
        //        2个
        List<String> list_2 = new ArrayList<>();
        list_2.add("x");
        list_2.add("xiao");
        list_2.add("x");
        list_2.add("x");
        assertEquals(2l,list_2.stream().distinct().count());

//        6个
        List<String> list_6 = new ArrayList<>();
        list_6.add("x");
        list_6.add("xiao");
        list_6.add("long");
        list_6.add("shi");
        list_6.add("da");
        list_6.add("shuai");
        list_6.add("shuai");
        list_6.add("shuai");
        list_6.add("shuai");
        assertEquals(6l,list_6.stream().distinct().count());

//        10个
        List<String> list_10 = new ArrayList<>();
        list_10.add("x");
        list_10.add("xiao");
        list_10.add("long");
        list_10.add("shi");
        list_10.add("da");
        list_10.add("shuai");
        list_10.add("ge");
        list_10.add("yin");
        list_10.add("jun");
        list_10.add("feng");
        list_10.add("x");
        list_10.add("x");
        assertEquals(10l,list_10.stream().distinct().count());
//        11个
        List<String> list_11 = new ArrayList<>();
        list_11.add("x");
        list_11.add("xiao");
        list_11.add("long");
        list_11.add("shi");
        list_11.add("da");
        list_11.add("shuai");
        list_11.add("ge");
        list_11.add("yin");
        list_11.add("jun");
        list_11.add("feng");
        list_11.add("sa");
        list_11.add("x");
        list_11.add("x");
        assertEquals(11l,list_11.stream().distinct().count());

        //        12个
        List<String> list_12 = new ArrayList<>();
        list_12.add("x");
        list_12.add("xiao");
        list_12.add("long");
        list_12.add("shi");
        list_12.add("da");
        list_12.add("shuai");
        list_12.add("ge");
        list_12.add("yin");
        list_12.add("jun");
        list_12.add("liu");
        list_12.add("sa");
        list_12.add("feng");
        assertEquals(12l,list_12.stream().distinct().count());

    }


//    参数化测试   低级
    @Test
    @ParameterizedTest
    @MethodSource("streamDistinct")
    void testDistinct(long expected, Stream stream){
        /*
         * 下界 0 1 2 个
         * 中间 6 个
         * 上界 10 11 12 个*/

        assertEquals(expected,stream.distinct().count());

    }
    static Stream<Arguments> streamDistinct() {
        Stream<String> s0 = Stream.of();
        Stream<String> s1 = Stream.of("A","A");
        Stream<String> s2 = Stream.of("A", "B");

        Stream<String> s6 = Stream.of("A", "B", "C", "D", "E", "F");

        Stream<String> s10 = Stream.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        Stream<String> s11 = Stream.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J","K");
        Stream<String> s12 = Stream.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J","K","L");
        return Stream.of(
                Arguments.of(0l,s0),
                Arguments.of(1l,s1),
                Arguments.of(2l,s2),

                Arguments.of(6l,s6),

                Arguments.of(10l,s10),
                Arguments.of(11l,s11),
                Arguments.of(12l,s12)
        );
    }
    
    //    参数化测试   都可以
    @Test
    @ParameterizedTest
    @MethodSource("streamDistinct02")
    void testDistinct02(long expected, Stream stream){
        /*
         * 下界 0 1 2 个
         *
         * 上界 4 5 6 个*/
        assertEquals(expected,stream.distinct().count());
    }
    static Stream<Arguments> streamDistinct02() {
        Stream<String> s0 = Stream.of();
        Stream<String> s1 = Stream.of("20010222","20010222");
        Stream<String> s2 = Stream.of("20010222", "20020222");

        Stream<String> s4 = Stream.of("20010222", "20020222", "20021126", "20010223");
        Stream<String> s5 = Stream.of("20010222", "20020222", "20021126", "20010223", "20010224");
        Stream<String> s6 = Stream.of("20010222", "20020222", "20021126", "20010223", "20010224", "20010225");
        return Stream.of(
                Arguments.of(0l,s0),
                Arguments.of(1l,s1),
                Arguments.of(2l,s2),

                Arguments.of(4l,s4),
                Arguments.of(5l,s5),
                Arguments.of(6l,s6)
        );
    }
}
