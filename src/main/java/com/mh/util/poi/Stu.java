package com.mh.util.poi;

/**
 * Created by Administrator on 2017/5/23.
 */
public class Stu extends BaseModel{
    private String name;
    private String age;

    public Stu(String id, String name, String age) {
        this.setId(id);
        this.name = name;
        this.age = age;
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
}
