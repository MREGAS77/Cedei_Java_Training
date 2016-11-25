package net.atos.cedei.cursospring.domain;

import java.io.Serializable;

import com.google.common.base.MoreObjects;

public class TaskEntity implements Serializable {

    private static final long serialVersionUID = -4445235454238177979L;

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
