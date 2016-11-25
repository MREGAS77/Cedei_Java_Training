package net.atos.cedei.cursospring.transform;

import java.util.ArrayList;
import java.util.List;

import net.atos.cedei.cursospring.domain.TaskEntity;
import net.atos.cedei.cursospring.vo.TaskVO;

public class TaskTransform {

    public static TaskEntity transformToEntity(TaskVO source) {
        if (source == null) {
            return null;
        }
        TaskEntity target = new TaskEntity();
        target.setId(source.getId());
        target.setName(source.getName());
        return target;
    }

    public static TaskVO transformToVO(TaskEntity source) {
        if (source == null) {
            return null;
        }
        TaskVO target = new TaskVO();
        target.setId(source.getId());
        target.setName(source.getName());
        return target;
    }

    public static List<TaskVO> transformToVO(List<TaskEntity> sources) {
        List<TaskVO> targets = new ArrayList<TaskVO>();
        if (sources != null) {
            for (TaskEntity source : sources) {
                TaskVO target = transformToVO(source);
                targets.add(target);
            }
        }
        return targets;
    }
}