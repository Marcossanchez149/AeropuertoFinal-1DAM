package org.example.Common;

public class IdVueloError extends Exception{
    public IdVueloError() {
        super("------El ID de vuelo introducido no es válido------");
    }

}
