package net.atos.cedei.cursospring.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import net.atos.cedei.cursospring.core.dao.TaskDAO;
import net.atos.cedei.cursospring.core.domain.TaskEntity;
import net.atos.cedei.cursospring.core.transform.TaskTransform;
import net.atos.cedei.cursospring.core.vo.TaskVO;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDAO taskDAO = null;

    public TaskDAO getTaskDAO() {
        return taskDAO;
    }

    public void setTaskDAO(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public TaskVO retrieveTask(Long id) {
        TaskEntity taskEntity = taskDAO.retrieveTask(id);
        TaskVO taskVO = TaskTransform.transformToVO(taskEntity);
        return taskVO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskVO> retrieveTasks() {
        List<TaskEntity> taskEntities = taskDAO.retrieveTasks();
        List<TaskVO> taskVOs = TaskTransform.transformToVO(taskEntities);
        return taskVOs;
    }

    @Override
    @Transactional
    public TaskVO createTask(TaskVO taskVO) {
        TaskEntity taskEntity = TaskTransform.transformToEntity(taskVO);
        TaskEntity taskEntityCreated = taskDAO.createTask(taskEntity);
        TaskVO taskVOCreated = TaskTransform.transformToVO(taskEntityCreated);
        return taskVOCreated;
    }

    @Override
    @Transactional
    public void updateTask(TaskVO taskVO) {
        TaskEntity taskEntity = TaskTransform.transformToEntity(taskVO);
        taskDAO.updateTask(taskEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskVO> findTasksByName(String name) {
        if (Strings.isNullOrEmpty(name)) {
            name = "%";
        }
        List<TaskEntity> taskEntities = taskDAO.findTasksByName(name);
        List<TaskVO> taskVOs = TaskTransform.transformToVO(taskEntities);
        return taskVOs;
    }

}
