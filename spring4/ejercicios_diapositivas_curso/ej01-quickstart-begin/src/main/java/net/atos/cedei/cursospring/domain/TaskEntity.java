package net.atos.cedei.cursospring.domain;

public class TaskEntity {
    private Long   id   = null;
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

}
