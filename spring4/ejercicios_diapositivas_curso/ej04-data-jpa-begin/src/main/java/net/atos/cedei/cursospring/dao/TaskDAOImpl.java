package net.atos.cedei.cursospring.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import net.atos.cedei.cursospring.domain.TaskEntity;

@Repository("taskDAO")
public class TaskDAOImpl implements TaskDAO {

    // TODO 10: Anotar atributo para inyectar el EntityManager
    private EntityManager entityManager = null;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<TaskEntity> retrieveTasks() {
        // TODO 4: Implementar método
        String hql = "FROM TaskEntity";
        throw new UnsupportedOperationException();
    }

    @Override
    public TaskEntity retrieveTask(Long id) {
        // TODO 5: Implementar método
        throw new UnsupportedOperationException();
    }

    @Override
    public TaskEntity createTask(TaskEntity taskEntity) {
        // TODO 6: Implementar método
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateTask(TaskEntity taskEntity) {
        // TODO 7: Implementar método
        throw new UnsupportedOperationException();
    }

    @Override
    public List<TaskEntity> findTasksByName(String name) {
        // TODO 8: Implementar método
        String hql = "FROM TaskEntity WHERE name like :name";
        throw new UnsupportedOperationException();
    }

}
