package net.atos.cedei.cursospring.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.atos.cedei.cursospring.domain.TaskEntity;

public class TaskRowMapper implements RowMapper<TaskEntity> {

    public static String COL_ID = "ID";
    public static String COL_NAME = "NAME";

    @Override
    public TaskEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(rs.getLong(COL_ID));
        taskEntity.setName(rs.getString(COL_NAME));
        return taskEntity;
    }

}
