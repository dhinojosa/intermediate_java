package com.xyzcorp;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LambdasTest {

    @Test
    public void testMyFilter() {
        List<Integer> numbers =  Arrays.asList(4, 5, 7, 8, 10, 11, 14, 15);

        List<Integer> result = Functions.myFilter(numbers, item -> item % 2 == 0);
        assertEquals(Arrays.asList(4, 8, 10, 14), result);
    }


    @Test
    public void testMyMap() {
        List<Integer> numbers =  Arrays.asList(4, 5, 7, 8);
        List<Integer> mapped = Functions.myMap(numbers, t -> t + 2);
        assertEquals(Arrays.asList(6, 7, 9, 10), mapped);
    }

    
    
    
}



