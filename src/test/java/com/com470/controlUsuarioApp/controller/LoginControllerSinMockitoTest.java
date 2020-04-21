
package com.com470.controlUsuarioApp.controller;

import java.security.cert.CertificateException;
import java.sql.Connection;
import javax.security.cert.X509Certificate;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginControllerSinMockitoTest {
    
    public LoginControllerSinMockitoTest() {
    }

    @Test
    public void testLogin() throws CertificateException, javax.security.cert.CertificateException{
        //
        byte [] certData;
        certData = new byte[0];
        X509Certificate certificado = X509Certificate.getInstance(certData);
        Connection connection=null;
        
        //probar
        LoginController utils = new LoginController(connection);
        boolean login = utils.login(certificado);
        //verificar
        assertTrue(login);
        
    }
    
}
