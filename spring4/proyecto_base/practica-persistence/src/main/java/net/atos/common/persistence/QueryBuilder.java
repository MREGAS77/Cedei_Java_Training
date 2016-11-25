package net.atos.common.persistence;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.NestedNullException;

/**
 * Helper para construir consultas eql/jpql dinámicas.
 */
public class QueryBuilder {

	/** Query con la consulta construida. */
	Query query = null;
	
	/** Query con la consulta que cuenta los registros. */
	Query countQuery = null;
	
	/** Jpql con el que se ejecutará la query. */
	StringBuilder jpql = new StringBuilder();
	
	/** Mapa con los parámetros para la query. */
	Map<Object, Object> parameterMap = new HashMap<Object, Object>();

	/** Mapa con los alias para la ordenaci�n. */
	Map<String, String> orderKeyMap = new HashMap<String, String>();

	/**
	 * Añade un trozo de Jpql a la Query
	 * 
	 * @param jpqlClause El trozo de Jpql.
	 */
	public void addJpql(String jpqlClause) {
		jpql.append(" ").append(jpqlClause);
	}

	/**
	 * Constructor que parte de una jpql inicial.
	 * 
	 * @param jpqlInicial La sentencia jpql inicial
	 */
	public QueryBuilder(String jpqlInicial) {
		jpql.append(jpqlInicial);
	}
	
	/**
	 * Añade un parámetro para la consulta.
	 * 
	 * @param paramName Nombre del parámetro.
	 * @param paramValue Valor del parámetro.
	 */
	public void addParameter(Object paramName, Object paramValue) {
		if (paramValue != null && (paramValue instanceof BigInteger)) {
			parameterMap.put(paramName, new BigDecimal(paramValue.toString()));
			return;
		}
		
//		if (paramValue != null && (paramValue instanceof Integer)) {
//			parameterMap.put(paramName, new BigDecimal(paramValue.toString()));
//			return;
//		}
//		
//		if (paramValue != null && (paramValue instanceof Long)) {
//			parameterMap.put(paramName, new BigDecimal(paramValue.toString()));
//			return;
//		}
		
		parameterMap.put(paramName, paramValue);
	}
	
	/**
	 * Añade una cláusula jpql condicional. Crea una nueva condición en la sentencia jpql
	 * con el parámetro.
	 * 
	 * @param jpqlClause Cláusula jpql a aplicar condicionalmente.
	 * @param paramName Nombre del parámetro.
	 * @param paramValue Valor del parámetro.
	 */
	public void addJpqlClause(String jpqlClause, Object paramName, Object paramValue) {
		addJpql(jpqlClause);
		addParameter(paramName, paramValue);
	}

	/**
	 * Añade una cláusula jpql condicional. Crea una nueva condición en la sentencia jpql
	 * con el parámetro si éste es no nulo.
	 * 
	 * @param jpqlClause Cláusula jpql a aplicar condicionalmente.
	 * @param paramName Nombre del parámetro.
	 * @param paramValue Valor del parámetro.
	 */
	public void addConditionalJpqlClause(String jpqlClause, Object paramName, Object paramValue) {
		addConditionalJpqlClause(jpqlClause, paramName, paramValue, paramValue!=null && !"".equals(paramValue.toString()));
	}
	
	/**
	 * Añade una cláusula jpql condicional. Crea una nueva condición en la sentencia jpql
	 * con el parámetro si éste es no nulo.
	 * 
	 * @param jpqlClause Cláusula jpql a aplicar condicionalmente.
	 * @param paramName Nombre del parámetro.
	 * @param paramValue Valor del parámetro.
	 * @param condition	
	 */
	public void addConditionalJpqlClause(String jpqlClause, Object paramName, Object paramValue, Boolean condition) {
		if (condition) {
			if (paramValue!=null) {
				
				if (paramValue instanceof Collection<?>  && ((Collection<?>) paramValue).size()==0){
					return;
				}
				addJpqlClause(jpqlClause, paramName, paramValue);
			}
		}
	}

	/**
	 * Añade una cláusula jpql condicional. Crea una nueva condición en la sentencia jpql
	 * con el parámetro si éste es no nulo. Hace throw de 3 excepciones que son las que
	 * implican un error al escribir la property.
	 * 
	 * @param jpqlClause Cláusula jpql a aplicar condicionalmente.
	 * @param paramName Nombre del parámetro.
	 * @param criteriaBean Objeto que contiene la propiedad a incluir como 
	 *        parámetro en alguno de sus atributos.
	 * @param property String que contiene la propiedad dentro de criteriaBean para ser
	 * 		  incluida como parámetro.
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public void addConditionalJpqlClause(String jpqlClause, String paramName, Object criteriaBean, String property) throws RuntimeException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if (criteriaBean != null) {
			Object paramValue = null;
			try {
				paramValue = BeanUtils.getProperty(criteriaBean, property);
			} catch (NestedNullException e) {
				return;
			}
			if (paramValue != null) {
				addJpqlClause(jpqlClause, paramName, paramValue);
			}
		}
	}

	
	/**
	 * Crea y a�ade una clausula jpql condicional de tipo IN. Crea una nueva condicion en la sentencia jpql
	 * con todos los  parametros suministrados. Automaticamente se a�ade la clausula IN(..) generando los 
	 * par�metros correspondientes a cada uno de los elementos de la colecci�n
	 * 
	 * @param jpqlClause Clausula parcial jpql a aplicar condicionalmente. No debe contener " in (...)" ya que lo generara el 
	 * @param paramNamePrefix Nombre con el que se generar�n los parámetros.
	 * @param paramValues Los distintos valores para generar la clausula IN.
	 */
	public <T extends Object> void  generateConditionalJpqlInClause(String jpqlClause, String paramNamePrefix, Collection<T> paramValues) {
		generateConditionalJpqlInClause(jpqlClause, paramNamePrefix, paramValues, false);
	}

	/**
	 * Crea y a�ade una clausula jpql condicional de tipo IN. Crea una nueva condicion en la sentencia jpql
	 * con todos los  parametros suministrados. Automaticamente se a�ade la clausula IN(..) generando los 
	 * par�metros correspondientes a cada uno de los elementos de la colecci�n.
	 * 
	 * Adicionalmente, se indica si se desea pasar como parametros HQL los distintos valores previamente convertidos a cadenas.
	 * 
	 * @param jpqlClause Clausula parcial jpql a aplicar condicionalmente. No debe contener " in (...)" ya que lo generara el 
	 * @param paramNamePrefix Nombre con el que se generar�n los parámetros.
	 * @param paramValues Los distintos valores para generar la clausula IN.
	 * @param convertValuesToString indica si se deben pasar como parametros HQL todos los valores previamente convertidos a cadenas.
	 */
	public <T extends Object> void generateConditionalJpqlInClause(String jpqlClause, String paramNamePrefix, Collection<T> paramValues,boolean convertValuesToString) {
		
		if (paramValues==null || paramValues.size()==0) {
			return;
		}
		if (paramValues.size()>1000) {
			throw new RuntimeException("JPA: IN sentence Max limit (> 1000 elements) exceeded on e");
		}
		
		StringBuffer jpqlClauseEx=new StringBuffer(jpqlClause);
		jpqlClauseEx.append(" in (");
		int paramCount=0;
		for (Object paramValue : paramValues) {
			if(paramCount!=0) {
				jpqlClauseEx.append(',');
			}
			String paramName=paramNamePrefix+paramCount;
			jpqlClauseEx.append(':').append(paramName);
			paramCount++;
			if (convertValuesToString) {
				addParameter(paramName, (paramValue==null)? null : paramValue.toString());
			}else{
				addParameter(paramName, paramValue);	
			}
			
		}
		jpqlClauseEx.append(")");
		addJpql(jpqlClauseEx.toString());
	}
	
	/**
	 * Obtiene la query a partir del jpql construido.
	 * 
	 * @param em El contexto de persistencia Seam.
	 * @param firstResult Primer resultado a traer de la base de datos.
	 * @param maxResults Número máximo de registros a traer de la base de datos.
	 * 
	 * @return La query.
	 */
	public Query getQuery(EntityManager em, Integer firstResult, Integer maxResults) {
		query = em.createQuery(jpql.toString());
		if (firstResult != null) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != null) {
			query.setMaxResults(maxResults); // Para saber si hay más registros en la base de datos.
		}
		for (Object paramName : parameterMap.keySet()) {
			//query.setParameter(paramName, parameterMap.get(paramName));
			setQueryParameter(query,paramName,parameterMap.get(paramName));
		}
		return query;
	}
	
	static protected void setQueryParameter(Query query,Object paramName,Object paramValue){
		if (paramName instanceof Integer) {
			//Query uses index parameters
			if (paramValue instanceof Date) {
				query.setParameter(Integer.parseInt(paramName.toString()),(Date) paramValue,TemporalType.TIMESTAMP);
			} else {
				query.setParameter(Integer.parseInt(paramName.toString()),paramValue);
			}
		} else if (paramValue == null || paramValue.equals("null")) {
			query.setParameter(paramName.toString(),"null");
		} else {
			if (paramValue instanceof Date) {
				query.setParameter(paramName.toString(),(Date) paramValue,TemporalType.TIMESTAMP);
			} else if (paramValue instanceof Collection<?>) {
				query.setParameter(paramName.toString(),(Collection<?>) paramValue);
			} else {
				query.setParameter(paramName.toString(),paramValue);
			}
		}
	}
	
	/**
	 * Obtiene la query.
	 * 
	 * @param em El contexto de persistencia de Seam.
	 * 
	 * @return La query.
	 */
	public Query getQuery(EntityManager em) {
		return getQuery(em, null, null);
	}

	
	/**
	 * Obtiene el número total de registros que devolvería la query (sin paginar).
	 * 
	 * @param em El contexto de persistencia de Seam.
	 * 
	 * @return El número total real de registros.
	 */
	public Long getRealCount(EntityManager em) {
//		int fromPosition = jpql.indexOf("from ");
//		String restoQuery = jpql.substring(fromPosition).replaceAll("fetch", "");
//		countQuery = em.createQuery("select count(" + "*" + ") " + restoQuery);
//		for (Object paramName : parameterMap.keySet()) {
//			setQueryParameter(countQuery,paramName, parameterMap.get(paramName));
//		}
//		return (Long)countQuery.getSingleResult();
		
		
		String query = jpql.toString().replaceAll("select\\s+([\\w|\\.]+)\\s+from", "select count($1) from")
				.replaceAll("fetch", "");
		
		if (query.indexOf("order by") > -1 ) {
			query = query.substring(0, query.indexOf("order by"));
		}
		
		countQuery = em.createQuery(query);
		for (Object paramName : parameterMap.keySet()) {
			setQueryParameter(countQuery,paramName, parameterMap.get(paramName));
		}
		return (Long)countQuery.getSingleResult();
		
	}
	
	/**
	 * Ejecuta la query calculando además el número total de registros que deviolvería. 
	 * Puede ser peligroso ejecutarlo para tablas muy grandes o con criterios muy complejos.
	 * Es responsabilidad del programador elegir si ejecutar la query con count automático o no. 
	 * 
	 * @param em El contexto de persistencia de Seam.
	 * @param firstResult El primer resultado a traer de la base de datos.
	 * @param maxResult El número máximo de resultados.
	 * 
	 * @return La página de resultados con el número total de registros.
	 */
	@SuppressWarnings("unchecked")
	public <T> QueryList<T> executeQueryWithCount(EntityManager em, Integer firstResult, Integer maxResult) {
		QueryList<T> ql = new QueryList<T>();
		ql.setTotalCount(getRealCount(em));
		ql.setResultList(getQuery(em, firstResult, maxResult).getResultList());
		return ql;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> executeQuery(EntityManager em) {
		return getQuery(em, null, null).getResultList();
	}
	
	/**
	 * Ejecuta la query y devuelve los resultados
	 * 
	 * @param em El contexto de persistencia de Seam.
	 * @param firstResult El primer resultado a traer de la base de datos.
	 * @param maxResult El número máximo de resultados.
	 * 
	 * @return La página de resultados con el número total de registros.
	 */

	@SuppressWarnings("unchecked")
	public <T> List<T> executeQuery(EntityManager em, Integer firstResult, Integer maxResult) {
		return getQuery(em, firstResult, maxResult).getResultList();
	}
	@SuppressWarnings("unchecked")
	public <T> T executeQueryFirstEntry(EntityManager em, Integer firstResult, Integer maxResult) {
		List<T> list= getQuery(em, firstResult, maxResult).getResultList();
		if (list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public <T>  T executeQuerySingle(EntityManager em) {
		return (T) getQuery(em, null ,null).getSingleResult();
	}
	
	public int executeUpdate(EntityManager em) {
		return getQuery(em, null ,null).executeUpdate();
	}
	
/*
	public <T> Cursor<T> scrollQuery(EntityManager em, CacheMode cacheMode,ScrollMode scrollMode) {
		org.hibernate.Query queryHib=getHibernateQuery(em, null, null);
		ScrollableResults results=queryHib.setCacheMode(cacheMode).scroll(scrollMode);
		return new Cursor<T>(results);
	}

	public <T> Cursor<T> scrollQuery(EntityManager em, Integer firstResult, Integer maxResults,CacheMode cacheMode,ScrollMode scrollMode) {
		org.hibernate.Query queryHib=getHibernateQuery(em, firstResult, maxResults);
		ScrollableResults results=queryHib.setCacheMode(cacheMode).scroll(scrollMode);
		return new Cursor<T>(results);
	}
*/
	
	public void addTableAlias(String nombreTabla, String jpqlAlias) {
		this.orderKeyMap.put(nombreTabla, jpqlAlias);
	}
	
	public void setOrderBy(String orderKey, boolean isAscending) {
		if (orderKey == null) {
			return;
		}

		jpql.append(" order by ");		
		String fields[]=orderKey.split(",");
		for (int i = 0; i < fields.length; i++) {
			if (i>0) {
				jpql.append(", ");
			}
			jpql.append(filterAlias(fields[i]));
			if (i==0 && !isAscending) {
				jpql.append(" DESC");
			}
		}
		
	}
	
	
	private String filterAlias(String orderField) {
		if (orderKeyMap.size() == 0) {
			return orderField;
		} 
		
		Set<String> aliasSet=orderKeyMap.keySet();
		for (String nombreTabla : aliasSet) {
			if (orderField.startsWith(nombreTabla)) {
				return orderField.replaceFirst(nombreTabla, orderKeyMap.get(nombreTabla));
			}
		}
		return orderField;
	}
	
	public boolean hasWhereClause() {
		if (parameterMap.size() > 0) {
			return true;
		} 
		return jpql.toString().toLowerCase().contains("where");
	}

	public Integer countWhereClause() {
		return parameterMap.size();
	}
	@Override
	public String toString() {
		return "QueryBuilder[jpql=" + jpql + ", parameterMap="+parameterMap.toString()+
				", orderKeyMap="+orderKeyMap.toString()+"]";
	}
	
	
//	private org.hibernate.Query getHibernateQuery(EntityManager em, Integer firstResult, Integer maxResults){
//	Session session=((Session)em.getDelegate());
//	org.hibernate.Query queryHib=session.createQuery(jpql.toString());
//	if (firstResult != null) {
//		queryHib.setFirstResult(firstResult);
//	}
//	if (maxResults != null) {
//		queryHib.setMaxResults(maxResults + 1); // Para saber si hay más registros en la base de datos.
//	}
//	for (String paramName : parameterMap.keySet()) {
//		queryHib.setParameter(paramName, parameterMap.get(paramName));
//	}
//	return queryHib;
//}
/*
	public String parseToSQL(EntityManager em){
      	
    	String stmtHQL=jpql.toString();
  		Session session=((Session)em.getDelegate());
//  		org.hibernate.Query queryHib=session.createQuery(stmtHQL);
  		final QueryTranslatorFactory translatorFactory = new ASTQueryTranslatorFactory();
  		final SessionFactoryImplementor factory = (SessionFactoryImplementor) session.getSessionFactory();
  		final QueryTranslator translator = translatorFactory.createQueryTranslator(
  				stmtHQL, stmtHQL,Collections.EMPTY_MAP, factory);
  		translator.compile(Collections.EMPTY_MAP, false);
  	    return translator.getSQLString();
	}
*/

	
}
