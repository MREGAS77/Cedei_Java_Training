package net.atos.cedei.cursospring.core.vo;

import java.io.Serializable;

import com.google.common.base.MoreObjects;

public class TaskVO implements Serializable {

    private static final long serialVersionUID = 1204009752354838627L;

    private Long id = null;
    private String name = null;

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
