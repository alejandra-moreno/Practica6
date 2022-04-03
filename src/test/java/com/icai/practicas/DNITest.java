package com.icai.practicas;

import com.icai.practicas.model.DNI;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

public class DNITest{

    @Test
    public void validar(){

        //Se van a probar distintos casos
        //1) DNI valido
        DNI dniCorrecto = new DNI("53938553B");
        Assert.assertEquals(true,dniCorrecto.validar());
        //2)DNI erroneo
        DNI dniErroneo = new DNI("123123123");
        Assert.assertEquals(false,dniErroneo.validar());
        //3.1)DNI no valido "00000000T"
        DNI dniInvalido1 = new DNI("00000000T");
        Assert.assertEquals(false,dniInvalido1.validar());
        //3.2)DNI no valido "00000001R"
        DNI dniInvalido2 = new DNI("00000001R");
        Assert.assertEquals(false,dniInvalido2.validar());
        //3.3)DNI no valido "99999999R"
        DNI dniInvalido3 = new DNI("99999999R");
        Assert.assertEquals(false,dniInvalido3.validar());
    }
}