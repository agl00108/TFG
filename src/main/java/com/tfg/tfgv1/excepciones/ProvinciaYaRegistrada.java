package com.tfg.tfgv1.excepciones;

public class ProvinciaYaRegistrada extends RuntimeException
{
    public ProvinciaYaRegistrada(String mensaje)
    {
        super(mensaje);
    }
}
