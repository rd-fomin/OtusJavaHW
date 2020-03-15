package com.remifo.asserts;

public class AssertEquals {

    public static void assertEquals(Object o1, Object o2) throws AssertEqualsException {
        if ( o1 == null )
            throw new AssertEqualsException( "First object is null" );
        if ( o2 == null )
            throw new AssertEqualsException( "Second object is null" );
        if ( !o1.equals( o2 ) )
            throw new AssertEqualsException( "First object is not equals to second object" );
    }

}
