package com.it593.dev.mobilistakip;

import java.util.Date;

public class Task {

    private static final long serialVersionUID = 1L;


    private String name;
    private String short_description;
    private String long_description;
    private Date created_date;
    private Date modified_date;
    private String location;
    private String task_statue;
    private Date start_date;
    private Date end_date;
    private int handled_by;
    private int created_by;
    private int modified_by;
    private String link;
    private String task_type;
    private String photo;
    private String _workOrderCount = "0";

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getLong_description() {
        return long_description;
    }

    public void setLong_description(String long_description) {
        this.long_description = long_description;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getModified_date() {
        return modified_date;
    }

    public void setModified_date(Date modified_date) {
        this.modified_date = modified_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTask_statue() {
        return task_statue;
    }

    public void setTask_statue(String task_statue) {
        this.task_statue = task_statue;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getHandled_by() {
        return handled_by;
    }

    public void setHandled_by(int handled_by) {
        this.handled_by = handled_by;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public int getModified_by() {
        return modified_by;
    }

    public void setModified_by(int modified_by) {
        this.modified_by = modified_by;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTask_type() {
        return task_type;
    }

    public void setTask_type(String task_type) {
        this.task_type = task_type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String get_workOrderCount() {
        return _workOrderCount;
    }

    public void set_workOrderCount(String _workOrderCount) {
        this._workOrderCount = _workOrderCount;
    }



    public Task() {
    }



}
