package com.xyzcorp;

import com.xyzcorp.people.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GenericsTest {
    @Test
    public void testBoxing() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(4);
        System.out.println(list.get(0) + 1);
    }

    @Test
    public void testUsingBox() {
        Box<Integer> box = new Box<>(4);
        Box<String> box2 = new Box<>("Howdy");
        Box<List<Integer>> box3 = new Box<>(Arrays.asList(1, 2, 3, 4, 5));
        Box<Box<Box<Integer>>> box5 = new Box<>(new Box<>(new Box<>(10)));
        System.out.println(box5.getContents().getContents().getContents());
    }

    @Test
    public void testInvariance() {
        //Call by site
        Box<Californian> boxOfCalifornians = new Box<Californian>();
        //Setters OK
        boxOfCalifornians.setContents(new Californian());
        //Getters OK
        Californian californian = boxOfCalifornians.getContents();
        System.out.println("boxOfCalifornians = " + boxOfCalifornians);
    }

    @Test
    public void testCovarianceAssignments() {
        Box<NorthernCalifornian> boxOfNorthernCalifornians = new Box<>();
        Box<? extends Californian> boxOfCalifornians = boxOfNorthernCalifornians;
        Box<? extends Object> boxOfObjects = boxOfNorthernCalifornians;
        Box<?> boxOfObjects2 = boxOfNorthernCalifornians;
        Box<? extends Person> boxOfPeople = boxOfNorthernCalifornians;
        Box<? extends American> boxOfAmericans = boxOfNorthernCalifornians;
        Box<? extends NorthernCalifornian> boxOfNorthernCalifornians2 = boxOfNorthernCalifornians;

        //Go over this
        Box<? extends NorthernCalifornian> box3 = new Box<Milpitasan>();
        NorthernCalifornian contents = box3.getContents();


        Box<? extends SanFranciscan> boxOfSanFranciscans  = boxOfNorthernCalifornians;
    }
}
