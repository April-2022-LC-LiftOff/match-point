package org.lauchcode.matchpoint.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Role {

    @Id
    private int id;

    @Enumerated(EnumType.STRING)
    private ERole name;

    public Role(){}

    public Role(ERole name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
