package com.remifo;

public class TestLogging implements TestLoggingInterface {

    @Log
    @Override
    public void calculation( int param1, String param2, boolean param3 ) { }

    @Log
    @Override
    public void calculation( int param ) { }

    @Override
    public void calculation(  ) { }

}
