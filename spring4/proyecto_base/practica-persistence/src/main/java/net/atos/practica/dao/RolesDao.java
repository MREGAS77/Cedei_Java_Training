package net.atos.practica.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.atos.common.base.BaseDao;
import net.atos.common.persistence.QueryBuilder;
import net.atos.practica.dto.FiltroRolesDto;
import net.atos.practica.entity.Rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation=Propagation.REQUIRED)
public class RolesDao extends BaseDao<Rol,String>{

	@Autowired
	@Qualifier("entityManager")
	public EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	public List<Rol> buscar(FiltroRolesDto filtro) {
		QueryBuilder qb = new QueryBuilder("Select distinct r from Rol r left join fetch r.permisos p where 1=1");
		qb.addConditionalJpqlClause("and upper(r.nombre) like :nombre", "nombre", "%" + filtro.getNombre() + "%", filtro.getNombre() != null && !"".equals(filtro.getNombre()));
		qb.addConditionalJpqlClause("and upper(r.descripcion) like :descripcion", "descripcion", "%" + filtro.getDescripcion() + "%", filtro.getDescripcion() != null && !"".equals(filtro.getDescripcion()));
		//qb.addConditionalJpqlClause("and u.nombre like :rol", "%rol%", filtro.getRol());
		return qb.executeQuery(em);		
	}
	
	public Rol buscarRolPorNombre(String nombre) {
		Rol r = em.find(Rol.class, nombre);
		return r;
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
}
