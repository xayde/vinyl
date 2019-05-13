package com.vinylstore.vinyl.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "role")
public class Role {
    public static final Role  USER = new Role(1,"user");
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "name")
    private String name;

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
