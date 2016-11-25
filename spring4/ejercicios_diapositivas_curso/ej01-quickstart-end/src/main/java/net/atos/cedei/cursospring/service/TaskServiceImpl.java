package net.atos.cedei.cursospring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.atos.cedei.cursospring.dao.TaskDAO;
import net.atos.cedei.cursospring.domain.TaskEntity;
import net.atos.cedei.cursospring.transform.TaskTransform;
import net.atos.cedei.cursospring.vo.TaskVO;

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
    public List<TaskVO> retrieveTasks() {
        List<TaskEntity> taskEntities = taskDAO.retrieveTasks();
        List<TaskVO> taskVOs = TaskTransform.transformToVO(taskEntities);
        return taskVOs;
    }

    @Override
    public List<TaskVO> findTasksByName(String name) {
        if (Strings.isNullOrEmpty(name)) {
            name = "^.*$";
        }
        if (!name.startsWith("^")) {
            name = "^".concat(name);
        }
        if (!name.endsWith("$")) {
            name = name.concat("$");
        }
        List<TaskEntity> taskEntities = taskDAO.findTasksByName(name);
        List<TaskVO> taskVOs = TaskTransform.transformToVO(taskEntities);
        return taskVOs;
    }

}
