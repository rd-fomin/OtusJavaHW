package remifo;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main( String[] args ) throws InterruptedException {
         calcInteger();
//        calcInt();
        System.out.println( "done" );
    }

    private static void calcInteger() throws InterruptedException {
        final int limit = Integer.MAX_VALUE;
        Integer counter = 0;
        List<User> users = new ArrayList<>();
        for ( int idx = 0; idx < limit; idx++ ) {

            counter += Integer.valueOf( 1 );
            users.add( new User( idx, counter + "", counter + 1_000 + "" ) );

            if ( idx % 1_000_000 == 0 ) {
                Thread.sleep( 1_000 );
            }

            if ( idx % 1_000 == 0 ) {
                for ( int i = 0; i < idx / 2; i++ ) {
                    users.remove( i );
                }
                idx -= idx / 2;
            }
        }
        System.out.println( counter );
    }

    private static void calcInt() throws InterruptedException {
        final int limit = Integer.MAX_VALUE;
        int counter = 0;
        for ( int idx = 0; idx < limit; idx++ ) {

            counter += 1;

            if ( idx % 1_000_000 == 0 ) {
                Thread.sleep( 1000 );
            }
        }
        System.out.println( counter );
    }
}
