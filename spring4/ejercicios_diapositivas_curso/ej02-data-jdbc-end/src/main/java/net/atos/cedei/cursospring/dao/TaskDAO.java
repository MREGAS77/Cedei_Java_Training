package net.atos.cedei.cursospring.dao;

import java.util.List;

import net.atos.cedei.cursospring.domain.TaskEntity;

public interface TaskDAO {

    /**
     * Retrieve one task
     * 
     * @return Task
     */
    public TaskEntity retrieveTask(Long id);

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

    /**
     * Create a new Task
     * 
     * @param TaskEntity
     * @return task created
     */
    public TaskEntity createTask(TaskEntity taskEntity);

    /**
     * Update a Task
     * 
     * @param TaskEntity
     */
    public void updateTask(TaskEntity taskEntity);
}
