package net.atos.cedei.cursospring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import net.atos.cedei.cursospring.dao.mapper.TaskRowMapper;
import net.atos.cedei.cursospring.domain.TaskEntity;

@Repository("taskDAO")
public class TaskDAOImpl implements TaskDAO {

    public static String SQL_SELECT_ONE = "SELECT * FROM T_TASKS WHERE " + TaskRowMapper.COL_ID + " = ?";
    public static String SQL_SELECT_ALL = "SELECT * FROM T_TASKS";
    public static String SQL_INSERT = "INSERT INTO T_TASKS(" + TaskRowMapper.COL_ID + ", " + TaskRowMapper.COL_NAME + ") VALUES (:id, :name)";
    public static String SQL_UPDATE = "UPDATE T_TASKS SET " + TaskRowMapper.COL_NAME + " = :name WHERE " + TaskRowMapper.COL_ID + " = :id";
    public static String SQL_SELECT_BY_NAME = "SELECT * FROM T_TASKS WHERE " + TaskRowMapper.COL_NAME + " like ?";
    public static String SQL_NEXT_ID = "call NEXT VALUE FOR S_TASKS_ID";

    protected TaskRowMapper taskRowMapper = new TaskRowMapper();

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate = null;

    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public List<TaskEntity> retrieveTasks() {
        // TODO 3: Implementar método retrieveTasks
        throw new UnsupportedOperationException();
    }

    @Override
    public TaskEntity retrieveTask(Long id) {
        // TODO 4: Implementar método retrieveTask
        throw new UnsupportedOperationException();
    }

    @Override
    public TaskEntity createTask(TaskEntity taskEntity) {
        // TODO 5: Implementar método createTask
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateTask(TaskEntity taskEntity) {
        // TODO 6: Implementar método updateTask
        throw new UnsupportedOperationException();
    }

    @Override
    public List<TaskEntity> findTasksByName(String name) {
        // TODO 7: Implementar método findTasksByName
        throw new UnsupportedOperationException();
    }

}
