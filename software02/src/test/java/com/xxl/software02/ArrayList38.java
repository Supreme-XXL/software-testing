package com.xxl.software02;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
public class ArrayList38 {

    @Test
    public void removeLanguige() {

//        删除为空的集合
        List<String> list1 = new ArrayList<>();
        Optional<String> firstElement = list1.stream().findFirst();
        if (firstElement.isPresent()) {
            list1.remove(0);
            assertEquals(-1, list1.size());
            log.info("list1: " + list1.toString());
        } else {
            assertEquals(0, list1.size());
            log.error("The list is empty. No element to remove.");
        }

        //        删除第一个元素
        List<String> list2 = new ArrayList<>();
        list2.add("x");
        list2.add("xiao");
        list2.add("long");
        list2.remove(0);
        log.info("list2: " + list2);
        assertEquals(2, list2.size());

//        删除中间元素
        List<String> list3 = new ArrayList<>();
        list3.add("x");
        list3.add("xiao");
        list3.add("long");
        list3.add("20102322");
        list3.add("2020134351");
        int index = 2;
        list3.remove(index);
        log.info("list3: " + list3);
        assertEquals(4, list3.size());
    }

    //  判定覆盖
    @Test
    public void removeJudge() {
//        false
        List<String> list1 = new ArrayList<>();
        list1.add("20102322");
        list1.remove(0);
        assertEquals(0, list1.size());
        //        true
        List<String> list2 = new ArrayList<>();
        list2.add("20102322");
        list2.add("2020134351");
        list2.remove(0);
        assertEquals(1, list2.size());
    }

    //    条件覆盖
    @Test
    public void removeCondition() {
//        false
        List<String> list1 = new ArrayList<>();
        list1.add("20102322");
        list1.remove(0);
        assertEquals(0, list1.size());
        //        true
        List<String> list2 = new ArrayList<>();
        list2.add("20102322");
        list2.add("2020134351");
        list2.remove(0);
        assertEquals(1, list2.size());
    }

    //    路径覆盖
    @Test
    public void removePath() {
//        超出异常   rangeCheck(index);
        List<String> list0 = new ArrayList<>();
        list0.add("20102322");
        try {
            list0.remove(100000000);
            assertEquals(0, list0.size());
        } catch (IndexOutOfBoundsException e) {
            log.info("您要删除的值超出了数组的长度，请重试");
            assertEquals(1, list0.size());
        }


        //        false
        List<String> list1 = new ArrayList<>();
        list1.add("20102322");
        list1.remove(0);
        assertEquals(0, list1.size());
        //        true
        List<String> list2 = new ArrayList<>();
        list2.add("20102322");
        list2.add("2020134351");
        list2.remove(0);
        assertEquals(1, list2.size());
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

}
