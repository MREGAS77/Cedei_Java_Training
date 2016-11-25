package net.atos.cedei.cursospring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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

    private TaskRowMapper taskRowMapper = new TaskRowMapper();

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
        List<TaskEntity> tasks = jdbcTemplate.query(SQL_SELECT_ALL, taskRowMapper);
        return tasks;
    }

    @Override
    public TaskEntity retrieveTask(Long id) {
        TaskEntity taskEntity = jdbcTemplate.getJdbcOperations().queryForObject(SQL_SELECT_ONE, new Object[] { id }, taskRowMapper);
        return taskEntity;
    }

    @Override
    public TaskEntity createTask(TaskEntity taskEntity) {
        Long nextId = jdbcTemplate.getJdbcOperations().queryForObject(SQL_NEXT_ID, Long.class);
        taskEntity.setId(nextId);

        jdbcTemplate.update(SQL_INSERT, new BeanPropertySqlParameterSource(taskEntity));

        return taskEntity;
    }

    @Override
    public void updateTask(TaskEntity taskEntity) {
        int rows = jdbcTemplate.update(SQL_UPDATE, new BeanPropertySqlParameterSource(taskEntity));
        if (rows != 1) {
            throw new IncorrectUpdateSemanticsDataAccessException("Zero or More than One Task updated");
        }
    }

    @Override
    public List<TaskEntity> findTasksByName(String name) {
        List<TaskEntity> result = jdbcTemplate.getJdbcOperations().query(SQL_SELECT_BY_NAME, new Object[] { name }, taskRowMapper);
        return result;
    }

}
