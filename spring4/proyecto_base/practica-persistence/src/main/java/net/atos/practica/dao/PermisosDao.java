package net.atos.practica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import net.atos.common.base.BaseDao;
import net.atos.common.persistence.QueryBuilder;
import net.atos.practica.dto.FiltroPermisosDto;
import net.atos.practica.entity.Permiso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional(propagation=Propagation.REQUIRED)
public class PermisosDao extends BaseDao<Permiso,String> {

	@Autowired
	@Qualifier("entityManager")
	public EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Permiso> buscar(FiltroPermisosDto filtro) {
		QueryBuilder qb = new QueryBuilder("Select distinct p from Permiso p left join fetch p.roles r where 1=1");
		qb.addConditionalJpqlClause("and upper(p.nombre) like :nombre", "nombre", "%" + filtro.getNombre() + "%", filtro.getNombre() != null && !"".equals(filtro.getNombre()));
		qb.addConditionalJpqlClause("and upper(p.descripcion) like :descripcion", "descripcion", "%" + filtro.getDescripcion() + "%", filtro.getDescripcion() != null && !"".equals(filtro.getDescripcion()));
		//qb.addConditionalJpqlClause("and u.nombre like :rol", "%rol%", filtro.getRol());
		return qb.executeQuery(em);		
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
}
