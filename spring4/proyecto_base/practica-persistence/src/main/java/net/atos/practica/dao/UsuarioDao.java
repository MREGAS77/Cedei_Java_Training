package net.atos.practica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import net.atos.common.base.BaseDao;
import net.atos.common.persistence.QueryBuilder;
import net.atos.practica.dto.FiltroUsuariosDto;
import net.atos.practica.entity.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation=Propagation.REQUIRED)
public class UsuarioDao extends BaseDao<Usuario,String> {
	
	@Autowired
	@Qualifier("entityManager")
	public EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Usuario> buscar(FiltroUsuariosDto filtro) {
		
		QueryBuilder qb = new QueryBuilder("Select u from Usuario u left join fetch u.rol r where 1=1");
		qb.addConditionalJpqlClause("and u.nombre like :nombre", "nombre", "%" + filtro.getNombre() + "%", filtro.getNombre() != null && !"".equals(filtro.getNombre()));
		qb.addConditionalJpqlClause("and u.codigo like :codigo", "codigo", "%" + filtro.getCodigo() + "%", filtro.getCodigo() != null && !"".equals(filtro.getCodigo()));
		qb.addConditionalJpqlClause("and u.nombre like :email", "email", "%" + filtro.getEmail() + "%", filtro.getEmail() != null && !"".equals(filtro.getEmail()));
		//qb.addConditionalJpqlClause("and u.nombre like :rol", "%rol%", filtro.getRol());
		return qb.executeQuery(em);
		
	}
	
	public Usuario buscarUsuarioPorUsernameYPassword(String username, String password) {
		
		QueryBuilder qb = new QueryBuilder("Select u from Usuario u left join fetch u.rol r where 1=1");
		qb.addConditionalJpqlClause("and u.nombre like :nombre", "nombre", username);
		qb.addConditionalJpqlClause("and u.passwd like :passwd", "passwd", password);
		List<Usuario> usuarios = qb.executeQuery(em);
		if (usuarios.size() == 1) {
			return usuarios.get(0);
		}
		return null;
		
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
}
