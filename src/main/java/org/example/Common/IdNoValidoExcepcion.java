package org.example.Common;

public class IdNoValidoExcepcion extends Exception{
    public IdNoValidoExcepcion() {
        super("------El DNI introducido no es válido------");
    }
}
