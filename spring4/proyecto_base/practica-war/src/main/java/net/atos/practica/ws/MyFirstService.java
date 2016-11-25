package net.atos.practica.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class MyFirstService {

	
	@WebMethod
	public int sumar(int op1, int op2) {
		System.out.println("SE EJECUTA");
		return op1 + op2;
	}
	
	@WebMethod
	public String sayHello() {
		return "HOLA";
	}
	
}
