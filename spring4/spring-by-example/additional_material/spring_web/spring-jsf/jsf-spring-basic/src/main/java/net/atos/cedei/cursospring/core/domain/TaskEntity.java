package net.atos.cedei.cursospring.core.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

@Table(name = "T_TASKS")
@Entity
public class TaskEntity implements Serializable {

    private static final long serialVersionUID = -4445235454238177979L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TASK_ID")
    @SequenceGenerator(name = "SEQ_TASK_ID", sequenceName = "S_TASKS_ID")
    private Long id = null;

    private String name = null;

    public TaskEntity() {
    }

    public TaskEntity(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).add("name", name).toString();
    }

}
