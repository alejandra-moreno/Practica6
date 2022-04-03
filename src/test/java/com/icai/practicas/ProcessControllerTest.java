package com.icai.practicas;

import com.icai.practicas.controller.ProcessController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcessControllerTest {
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void given_app_when_login_using_right_credencials_then_ok() throws Exception{
        
        String address = "http://localhost:"+port+"/api/v1/process-step1";

        //Request con nombre, dni y telefono
        ProcessController.DataRequest dataPrueba = new ProcessController.DataRequest("Alejandra", "53938553B", "662306135");
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(dataPrueba, headers);

        //Response
        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);
        then(result.getBody()).isEqualTo("{\"result\":\"OK\"}");
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

    @Test
    public void given_app_when_login_using_right_credencials_then_ko() throws Exception{
        
        String address = "http://localhost:"+port+"/api/v1/process-step1";

        //Request con dni erroneo
        ProcessController.DataRequest dataDNIError = new ProcessController.DataRequest("Alejandra", "99999999R", "662306135");
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProcessController.DataRequest> requestDNIError = new HttpEntity<>(dataDNIError, headers);

        //Response
		ResponseEntity<String> resultErrorDNI = this.restTemplate.postForEntity(address, requestDNIError, String.class);
        then(resultErrorDNI.getBody()).isEqualTo("{\"result\":\"KO\"}");
        then(resultErrorDNI.getStatusCode()).isEqualTo(HttpStatus.OK);

        //Request con telefono erroneo
        ProcessController.DataRequest dataTelefonoError = new ProcessController.DataRequest("Alejandra", "53938553B", "12312312A");

        HttpEntity<ProcessController.DataRequest> requestTelefonoError = new HttpEntity<>(dataTelefonoError, headers);

        //Response
        ResponseEntity<String> resultTelefonoError = this.restTemplate.postForEntity(address, requestTelefonoError, String.class);
        then(resultTelefonoError.getBody()).isEqualTo("{\"result\":\"KO\"}");
        then(resultTelefonoError.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void given_app_when_login_using_right_credentials_then_ok_legacy() throws Exception{
            
            String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";

            //Datos correctos
            MultiValueMap<String, String> datosCorrectos = new LinkedMultiValueMap<>();
            datosCorrectos.add("fullName", "Alejandra");
            datosCorrectos.add("dni", "53938553B");
            datosCorrectos.add("telefono", "662306135");
            
            //Request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> requestCorrecto = new HttpEntity<>(datosCorrectos, headers);

            //Response
            ResponseEntity<String> resultCorrecto = this.restTemplate.postForEntity(address, requestCorrecto, String.class);
            then(resultCorrecto.getBody()).contains("Muchas gracias por enviar los datos");
            then(resultCorrecto.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void given_app_when_login_using_right_credentials_then_ko_legacy() throws Exception{
            
            String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";

            //DNI erroneo
            MultiValueMap<String, String> datosDNIErroneo = new LinkedMultiValueMap<>();
            datosDNIErroneo.add("fullName", "Alejandra");
            datosDNIErroneo.add("dni", "123456789");
            datosDNIErroneo.add("telefono", "662306135");

            //El número introducido es incorrecto
            MultiValueMap<String, String> datosNumeroIncorrecto = new LinkedMultiValueMap<>();
            datosNumeroIncorrecto.add("fullName", "Alejandra");
            datosNumeroIncorrecto.add("dni", "53938553B");
            datosNumeroIncorrecto.add("telefono", "12312312A");

            //Request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> requestDNIError = new HttpEntity<>(datosDNIErroneo, headers);
            HttpEntity<MultiValueMap<String, String>> requestErrorTelefono = new HttpEntity<>(datosNumeroIncorrecto, headers);
            //Response
            ResponseEntity<String> resultErrorDNI = this.restTemplate.postForEntity(address, requestDNIError, String.class);
            ResponseEntity<String> resultErrorTelefono = this.restTemplate.postForEntity(address, requestErrorTelefono, String.class);
            
            then(resultErrorDNI.getBody()).contains("Hemos tenido un problema con su solicitud");
            then(resultErrorTelefono.getBody()).contains("Hemos tenido un problema con su solicitud");
            then(resultErrorDNI.getStatusCode()).isEqualTo(HttpStatus.OK);
            then(resultErrorTelefono.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
