package com.icai.practicas;

import com.icai.practicas.model.Telefono;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

public class TelefonoTest {

    @Test
    public void validar(){
        
        //Se van a probar distintos casos
        //1) Telefono valido
        Telefono telefonoValido = new Telefono("662306135");
        Assert.assertEquals(true,telefonoValido.validar());
        //2) Telefono con prefijo
        Telefono telefonoPrefijo = new Telefono("+34662306135");
        Assert.assertEquals(true,telefonoPrefijo.validar());
        //3) Telefono con letra
        Telefono telefonoLetra = new Telefono("66230613A");
        Assert.assertEquals(false,telefonoLetra.validar());
        //4) Telefono demasiado corto
        Telefono telefonoCorto = new Telefono("662306");
        Assert.assertEquals(false,telefonoCorto.validar());
        //5) Telefono demasiado largo
        Telefono telefonoLargo = new Telefono("662306135123");
        Assert.assertEquals(false,telefonoLargo.validar());
    }
    
}
