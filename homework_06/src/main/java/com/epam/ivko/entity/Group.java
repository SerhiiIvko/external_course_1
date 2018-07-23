package com.epam.ivko.entity;

import java.util.Date;

public class Group {
    private int id;
    private String name;
    private Date startDate;
    private String curator;

    public Group(int id, String name, Date startDate, String curator) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.curator = curator;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getCurator() {
        return curator;
    }

    public void setCurator(String curator) {
        this.curator = curator;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", curator='" + curator + '\'' +
                '}';
    }
}
