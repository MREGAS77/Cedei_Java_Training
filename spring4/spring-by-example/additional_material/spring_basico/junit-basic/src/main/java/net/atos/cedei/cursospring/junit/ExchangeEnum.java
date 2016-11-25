package net.atos.cedei.cursospring.junit;
/***
 * 
 * @author
 * @TODO Añadir más divisas?
 * Enum donde se registran los distintos tipos de divisas.
 *
 */
public enum ExchangeEnum {
 
	
	EUR(1,"Euro"),
	USD(2, "American Dollar");
	
	private final String tipo;
	private final Integer id;


	private ExchangeEnum(Integer id, String tipo) {
		this.id = id;
		this.tipo = tipo;
	}
	
	public final String getTipo() {
		return tipo;
	}
	public final Integer getId() {
		return id;
	}
	
	/***
	 * 
	 * @param tipo 
	 * @return 
	 * @throws Exception
	 */
	public static ExchangeEnum getFromTipo(String tipo) throws Exception {
		
		for(ExchangeEnum divisa : ExchangeEnum.values()) {
			if(divisa.getTipo().equalsIgnoreCase(tipo)) {
				return divisa;
			}
		}
		throw new Exception("No existe el tipo divisa " + tipo);
	}
	
	/***
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static ExchangeEnum getFromId(Integer id) throws Exception {
		
		for(ExchangeEnum divisa : ExchangeEnum.values()) {
			if(divisa.getId().equals(id)) {
				return divisa;
			}
		}
		throw new Exception(
				"No existe el tipo divisa " + id);
	}
}