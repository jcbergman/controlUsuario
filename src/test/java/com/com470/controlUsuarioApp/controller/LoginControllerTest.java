
package com.com470.controlUsuarioApp.controller;

import java.security.Principal;
import java.security.cert.CertificateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.security.cert.X509Certificate;
import org.junit.Test;
import org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import org.mockito.ArgumentMatchers;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginControllerTest {
    
    

    @Test
	public void testLoginConCertificado() throws javax.security.cert.CertificateException, SQLException {
		// Preparar
                    //crear un objeto atravez de mockito, se le pasa una clase de java X50
		X509Certificate certificado = mock(X509Certificate.class);
                //crer un objeto que  auque no se tiene la implementacion
		Connection connection = mock(Connection.class);
		
		// Programar respuestas
                  //2da parte de mockito que cosa, queremos que nos devuelva esa clase simulada, 
                  //conforme se llame a sus metodos
                  //TESTEO DE CAJA BLANCA
                  //-->para saber exactamente que cosas tengo que programar en estos 2 objetos Certificado y conection
                  //tengo que saber que cosas se van a ir llamando 2 formas:
//1.inspeccionamos en codigo fuente 2.pasar todo esto ir directo a //probar ver que pasa*/
		Principal principal = mock(Principal.class);
		when(certificado.getSubjectDN()).thenReturn(principal);
		when(principal.getName()).thenReturn("usuario");
		
		PreparedStatement preparedStatement = mock(PreparedStatement.class);
                //cuando al objeto mock se llame al metodo con cualquier parametro 
                //estamos diciendo a mockito que cuando llamemos a preparestatemt con cualquier string
                //debe devolver la clase emulada
                //ademas que se ejecuta un query
		when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
                //busca si coincide con ese usuario, si es asi el count es +1 
                //la qery consulta y debe devolver que es mayor que cero 
		ResultSet resultSet = mock(ResultSet.class);
		when(preparedStatement.executeQuery()).thenReturn(resultSet);
		when(resultSet.next()).thenReturn(true);
		when(resultSet.getInt(1)).thenReturn(1);
		
		// Probar
		LoginController controller = new LoginController(connection);
		boolean login = controller.login(certificado);
		
		// Verificar
		assertTrue(login);
	}
        
       
}
