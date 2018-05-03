package com.mh.util.poi.mypoi;

import com.mh.util.poi.BaseModel;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/23.
 */
public class Stu{
    private String id;
    private String name;
    private String age;
    private Date birthday;

    public Stu(String id, String name, String age, Date birthday) {
        this.setId(id);
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
