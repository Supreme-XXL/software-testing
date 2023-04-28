package com.xxl.software02;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class Software02ApplicationTests {

    private  List<String> arrayListBefore ;
    private  List<String> arrayListAfter ;


    @Test
    void contextLoads() {
    }

//    黑盒测试
    @Test
    @ParameterizedTest
    @MethodSource("streamArgument")
    void streamTest(long expected, Stream stream) {
        assertEquals(expected,stream.distinct().count());
    }

    static Stream<Arguments> streamArgument() {
        Stream<String> s1 = Stream.of("A", "B", "A", "B", "A", "B", "A", "B", "A", "B");
        Stream<String> s2 = Stream.of("A", "B", "C", "B", "A", "B", "A", "B", "A", "B");
        Stream<String> s3 = Stream.of("A", "B", "C", "D", "A", "B", "A", "B", "A", "B");
        Stream<String> s4 = Stream.of("A", "B", "C", "D", "E", "B", "A", "B", "A", "B");
        Stream<String> s0 = Stream.of();
        Stream<String> s11 = Stream.of("A","A","A","A","A","A","A","A");
        return Stream.of(
                Arguments.of(2l,s1),
                Arguments.of(0l,s0),
                Arguments.of(1l,s11),
                Arguments.of(3l,s2),
                Arguments.of(4l,s3),
                Arguments.of(5l,s4)
        );
    }

    
    /*
    * public E remove(int index) {
        rangeCheck(index);

        modCount++;
        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }*/




    @BeforeEach
    void setUp() throws IOException {
        // 读取 stream.csv 文件中的所有行
        try (InputStream inputStream = Software02ApplicationTests.class.getResourceAsStream("/stream.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            arrayListBefore = reader.lines().collect(Collectors.toList());
        }
    }
    //    白盒测试

    @Test
    @ParameterizedTest
    @CsvFileSource(resources = "/stream.csv")
    void removeTest01(String data){
        log.info(arrayListBefore.toString());
        log.info(arrayListBefore.size()+" size1");
        // 在测试方法中使用 arrayListBefore 列表
        assertEquals(5, arrayListBefore.size());

        arrayListAfter =  arrayListBefore;

        // 测试删除负数索引是否会抛出异常
        assertThrows(IndexOutOfBoundsException.class, () -> arrayListBefore.remove(-10));

        // 测试删除超出范围的索引是否会抛出异常
        assertThrows(IndexOutOfBoundsException.class, () -> arrayListBefore.remove(10));

        // 测试删除第一个元素
        arrayListBefore.remove(0);
        assertEquals(4, arrayListBefore.size());
        assertEquals("\"xiao\"", arrayListBefore.get(0));

        // 测试删除最后一个元素
        arrayListBefore.remove(arrayListBefore.size() - 1);
        assertEquals(3, arrayListBefore.size());
        assertEquals("\"is\"", arrayListBefore.get(2));

        // 测试删除中间的元素
        arrayListBefore.remove(1);
        log.info(arrayListBefore.toString()+" now");
        assertEquals(2, arrayListBefore.size());
        assertEquals("\"xiao\"", arrayListBefore.get(0));
        assertEquals("\"is\"", arrayListBefore.get(1));
        
        log.info(arrayListAfter.toString());
        log.info(arrayListAfter.size()+" size2");
    }

//    单元测试(distinct) 低配版
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

    //    单元测试(distinct)  正常版
    @Test
    void testDistinct02(){

        /*
         * 下界 null  1  相同和不同个
         *
         * 上界 1000000 999999 1000001 相同和不同个*/
        List<Integer> listNull = new ArrayList<>();
        listNull.add(null);
        assertEquals(1l,listNull.stream().distinct().count());

        List<Integer> list_1 = new ArrayList<>();
        list_1.add(1);
        list_1.add(1);
        assertEquals(1l,list_1.stream().distinct().count());


         List<Integer> list_1_ch = new ArrayList<>();
        list_1_ch.add(1);
        list_1_ch.add(2);
        assertEquals(2l,list_1_ch.stream().distinct().count());

//        100万个值为1的List副本
        List<Integer> list1 = Collections.nCopies(1000000, 1);
        List<Integer> list2 = Collections.nCopies(999999, 1);
        List<Integer> list3 = Collections.nCopies(1000001, 1);
        assertEquals( 1l,list1.stream().distinct().count());
        assertEquals( 1l,list2.stream().distinct().count());
        assertEquals( 1l,list3.stream().distinct().count());

//        100万个不同整数值的List
        List<Integer> list4 = IntStream.range(0, 1000000).boxed().collect(Collectors.toList());
        List<Integer> list5 = IntStream.range(0, 999999).boxed().collect(Collectors.toList());
        List<Integer> list6 = IntStream.range(0, 1000001).boxed().collect(Collectors.toList());
        assertEquals( 1000000l,list4.stream().distinct().count());
        assertEquals( 999999,list5.stream().distinct().count());
        assertEquals( 1000001,list6.stream().distinct().count());


    }

}
