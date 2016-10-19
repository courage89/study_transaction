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
    private Byte age;
    public static final String AGE = "age";
    private String name;
    public static final String NAME = "name";

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
    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TSimpleUserEntity that = (TSimpleUserEntity) o;

        if (id != that.id) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
