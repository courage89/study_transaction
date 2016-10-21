package com.qingqing.study.domain;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "t_simple_user", schema = "qq_study", catalog = "")
public class TSimpleUserEntity {
    private int id;
    public static final String ID = "id";
    private int age;
    public static final String AGE = "age";
    private String name;
    public static final String NAME = "name";
    private boolean isDeleted;
    public static final String IS_DELETED= "is_deleted";

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "is_deleted", nullable = false, length = 50)
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
