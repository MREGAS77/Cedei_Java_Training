package net.atos.common.base;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.Query;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;


/**
 * Session Bean implementation class DeviceBean Implements the business
 * functions of security devices management of the GAMMA application
 */
public abstract class BaseDao<T, K> {

	protected Class<T> entityClass;

	protected abstract EntityManager getEntityManager();

	// private final static Logger logger =
	// Logger.getLogger(BaseBean.class.getName());

	
	
	private List<String> obtainToOneEntities() {
		List<String> list = new ArrayList<String>();
		Field[] fields = entityClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field m = fields[i];
			Annotation a = m.getAnnotation(ManyToOne.class);
			if (a == null) continue;
			list.add(m.getName());
		}
		return list;
	}

//	private List<String> obtainToManyEntities() {
//		List<String> list = new ArrayList<String>();
//		Field[] fields = entityClass.getDeclaredFields();
//		for (int i = 0; i < fields.length; i++) {
//			Field m = fields[i];
//			Annotation a = m.getAnnotation(ManyToMany.class);
//			if (a == null) continue;
//			list.add(m.getName());
//		}
//		return list;
//	}

	
	
	
	/**
	 * Default constructor of SecurityBean Initializes the the entity manager
	 * for the JPA persistence with the Oracle Data Base through the WAS data
	 * source
	 */
	@SuppressWarnings("unchecked")
	public BaseDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	/***
	 * Insert or Update any Entity
	 * 
	 * @param obj
	 * @return same object with updated or inserted
	 * 
	 */
	public <E> E insertOrUpdate(E obj) {
		
		obj = getEntityManager().merge(obj);
		getEntityManager().flush();
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		Query q = getEntityManager().createQuery("select obj from " + entityClass.getName() + " obj");
		return q.getResultList();
	}
	

	public Boolean deleteOneById(K id) {
		int result = -1;

		try {
		
			Query q = getEntityManager().createQuery("delete from " + entityClass.getName() + " obj where obj." + getIdProperty() + " = :id");
			q.setParameter("id", id);
			result = q.executeUpdate();
			getEntityManager().flush();
		} catch (Exception e) {
			
			if (hasCause(e, SQLIntegrityConstraintViolationException.class)) {
				throw new RuntimeException("Hay referencias al elemento y por lo tanto no se puede eliminar", e);
			}
			throw new RuntimeException("No se ha podido eliminar el elemento", e);
		}

		return result > 0; //True if insert more than one rows False otherwise
	}

	@SuppressWarnings("unchecked")
	public Boolean deleteOne(T entity) {
		return deleteOneById((K)getIdValue(entity));
	}


	public T getReference(K id) {
		T obj = null;
		obj = getEntityManager().getReference(entityClass, id);
		return obj;
	}
	
	private static Boolean hasCause(Throwable t, Class<?> cause) {
		if (cause.isInstance(t)) {
			return true;
		}
		
		if (t.getCause() != null) {
			return hasCause(t.getCause(), cause);
		}
		return false;
		
	}
	
	public void refresh(Object entity) {
		getEntityManager().refresh(entity);
	}
	
	public T getOneRefresh(K id) {
		T obj = null;
		obj = getEntityManager().find(entityClass, id);
		getEntityManager().refresh(obj);
		return obj;
	}
	
	public T getOne(K id) {
		T obj = null;
		obj = getEntityManager().find(entityClass, id);
		return obj;
	}
	
	public T getByCode(String code) {
		return findOneByAttr("code", code);
	}

	
	public <E> E getReference(Class<E> cls,K id) {
		E obj = getEntityManager().getReference(cls, id);
		return obj;
	}
	
	
	public <E> E getOne(Class<E> cls,K id) {
		E obj = getEntityManager().find(cls, id);
		return obj;
	}
	
	public <E> E getOneRefresh (Class<E> cls,K id) {
		E obj = getEntityManager().find(cls, id);
		getEntityManager().refresh(obj);
		return obj;
	}
	
	public <E extends Object> List<E>  detach(List<E> entityList){
		for (E entity : entityList) {
			getEntityManager().detach(entity);	
		}
		return entityList;
	}
	
	public <E extends Object> E detach(E entity){
		getEntityManager().detach(entity);
		return entity;
	}

	
	@SuppressWarnings("unchecked")
	public List<T> find(String field, Object value) {
		StringBuffer query = new StringBuffer();
		String parameter = field.replace('.', '_');
		query.append("select obj from ").append(entityClass.getName()).append(" obj where obj.").append(field).append(" = :")
				.append(parameter);

		Query q = getEntityManager().createQuery(query.toString());
		q.setParameter(parameter, value);
		return q.getResultList();
	}

	public T findOneByAttr(String field, Object value) {

		List<T> resulList = find(field, value);
		if (resulList != null && resulList.size() > 0) {
			return (T) resulList.get(0);
		}
		return null;

//		StringBuffer query = new StringBuffer();
//		String parameter = field.replace('.', '_');
//		query.append("select obj from ").append(entityClass.getName()).append(" obj where obj.").append(field).append(" = :")
//				.append(parameter);
//
//		Query q = getEntityManager().createQuery(query.toString());
//		q.setParameter(parameter, value);
//		return (T) q.getSingleResult();
	}

	
	
	protected String getIdProperty() {
        String idProperty=null;
        Metamodel metamodel = getEntityManager().getMetamodel();
        EntityType<T> entity = metamodel.entity(entityClass);
        Set<SingularAttribute<? super T,?>> singularAttributes = entity.getSingularAttributes();
        for (SingularAttribute<? super T,?> singularAttribute : singularAttributes) {
            if (singularAttribute.isId()){
                idProperty=singularAttribute.getName();
                break;
            }
        }
        if(idProperty==null){
            throw new RuntimeException("[BaseDao] Entity Id field not found");
        }
        return idProperty;
    }
	
	
	protected Object getIdValue(T entity) {
        String idProperty=getIdProperty();
        return BeanUtils.getProperty(entity, getIdProperty());
    }
	
	

}
