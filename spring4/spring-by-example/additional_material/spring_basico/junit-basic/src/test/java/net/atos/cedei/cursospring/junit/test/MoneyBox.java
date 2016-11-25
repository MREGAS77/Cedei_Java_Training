package net.atos.cedei.cursospring.junit.test;

public class MoneyBox {

	private double amount = 0;


	public Object getAmount() {
		// TODO Auto-generated method stub
		return amount;
	}

	public void save(double saveMoney, String tipo) {
		amount=saveMoney;		
	}

}
