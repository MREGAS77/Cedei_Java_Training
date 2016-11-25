package net.atos.cedei.cursospring.dao;

import java.util.List;

import net.atos.cedei.cursospring.domain.TaskEntity;

public interface TaskDAO {

    /**
     * Retrieve all tasks
     * 
     * @return Task List
     */
    public List<TaskEntity> retrieveTasks();

    /**
     * Find task by name
     * 
     * @param name
     * @return Task List
     */
    public List<TaskEntity> findTasksByName(String name);
}
