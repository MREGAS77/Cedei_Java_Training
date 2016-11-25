package net.atos.cedei.cursospring.junit.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import junit.framework.TestCase;
import net.atos.cedei.cursospring.junit.ExchangeEnum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @see http://stackoverflow.com/questions/3139879/how-do-i-get-currency-exchange-rates-via-an-api-such-as-google-finance
 * 
 * Un cliente nos solicita saber el cambio de divisa de Euros a Dólares porque viaja con frecuencia a EEUU.
 *  
 * Requisitos iniciales:
 * 
 *                       1) Nos piden generar una aplicacion utilidad para convertir de Euros a Dolares.
 *                       2) Hemos de utilizar la api de Yahoo como conversor, ya que, según el cliente, tiene el cambio de divisa totalmente actualizado.
 *
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_Test.xml")
public class MoneyBoxTest extends TestCase {

	private static final Logger logger = LoggerFactory.getLogger(MoneyBoxTest.class);

	@Before
	public void setUp() throws Exception {
		super.setUp();	
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 *  REQUISITOS EXTRAIDOS EN PRIMERA REUNION CON EL CLIENTE:
	 *  El cliente quiere una aplicacion para gestionar sus ahorros en una hucha virtual
	 *  
	 *  La aplicacion ha de permitir :
	 *  			 guardar dinero.
	 *               guardar dinero, en distintas divisas.
	 *  			 consultar los ahorros.
	 *  			 consultar ahorros in distintas divisas.
	 *               consultar los movimientos realizados.
	 *  
	 */
	
	/***
	 * CASO 1.GUARDAR DINERO EN LA HUCHA
	 * @TODO
	 */
	@Test
	public void saveMoneyTest(){
		double amount = 23.5;
		
		MoneyBox moneyBox = new MoneyBox();
		moneyBox.save(amount,ExchangeEnum.EUR.getTipo());
		assertEquals("La cantidad en la hucha no es la esperada",23, moneyBox.getAmount());
	}
}

