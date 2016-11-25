package net.atos.cedei.cursospring.core.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import net.atos.cedei.cursospring.core.domain.TaskEntity;

@Repository("taskDAO")
public class TaskDAOImpl implements TaskDAO {

    @PersistenceContext
    private EntityManager entityManager = null;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<TaskEntity> retrieveTasks() {
        String hql = "FROM TaskEntity";
        TypedQuery<TaskEntity> query = entityManager.createQuery(hql, TaskEntity.class);
        List<TaskEntity> tasks = query.getResultList();
        return tasks;
    }

    @Override
    public TaskEntity retrieveTask(Long id) {
        TaskEntity taskEntity = entityManager.find(TaskEntity.class, id);
        return taskEntity;
    }

    @Override
    public TaskEntity createTask(TaskEntity taskEntity) {
        entityManager.persist(taskEntity);
        return taskEntity;
    }

    @Override
    public void updateTask(TaskEntity taskEntity) {
        entityManager.merge(taskEntity);
    }

    @Override
    public List<TaskEntity> findTasksByName(String name) {
        String hql = "FROM TaskEntity WHERE name like :name";
        TypedQuery<TaskEntity> query = entityManager.createQuery(hql, TaskEntity.class);
        query.setParameter("name", name);
        List<TaskEntity> result = query.getResultList();
        return result;
    }

}
