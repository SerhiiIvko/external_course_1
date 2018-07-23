package com.epam.ivko.entity;

import java.util.Date;

public class Student {
    private int id;
    private String name;
    private String surname;
    private Date birthday;
    private Group group;

    public Student(int id, String name, String surname, Date birthday, Group group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", group=" + group +
                '}';
    }
}
