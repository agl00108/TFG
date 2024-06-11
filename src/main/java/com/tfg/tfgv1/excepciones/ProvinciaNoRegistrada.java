package com.tfg.tfgv1.excepciones;

public class ProvinciaNoRegistrada extends RuntimeException
{
    public ProvinciaNoRegistrada(String mensaje)
    {
        super(mensaje);
    }
}
