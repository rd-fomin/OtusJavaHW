package com.remifo;

import com.remifo.annotations.After;
import com.remifo.annotations.Before;
import com.remifo.annotations.Test;
import com.remifo.asserts.AssertEquals;

public class ClassTest {

    @Test
    private void functionOne(  ) { }

    @After
    private void functionTwo(  ) {
        System.out.println("After");
    }

    @Test
    private void functionThree(  ) {
        Object o1 = new Object();
        Object o2 = null;
        AssertEquals.assertEquals( o1, o2 );
    }

    @Test
    private void functionFour(  ) {
        throw new RuntimeException("some error for test TWO");
    }

    @Before
    private void functionFive(  ) {
        System.out.println("Before");
    }

    @Test
    private void functionSix(  ) { }

}
