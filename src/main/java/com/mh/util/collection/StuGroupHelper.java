package com.mh.util.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对LIST进行分组
 */
public class StuGroupHelper {

    public static Map<Integer, List<Stu1>> group(List<Stu1> list){
        Map<Integer, List<Stu1>> map = new HashMap<>();
        for(Stu1 stu1 : list){
            List<Stu1> groupList;
            if(map.get(stu1.getGroup()) == null){
                map.put(stu1.getGroup(), new ArrayList<Stu1>());
            }
            groupList = map.get(stu1.getGroup());
            groupList.add(stu1);
        }
        return map;
    }

    public static void main(String[] args) {
        List<Stu1> list = new ArrayList<>();
        char ch= 65;
        for(int i = 0; i < 100; i++){
            int i1 = i % 10;
            list.add(new Stu1(i, String.valueOf((char)ch+i), i1));
        }

        Map<Integer, List<Stu1>> group = StuGroupHelper.group(list);

        for(Integer gr : group.keySet()){
            List<Stu1> stu1s = group.get(gr);
            System.out.println(stu1s);
            System.out.println("=======================================");
        }

        System.out.println("=======================================");

    }

}

class Stu1{
    private Integer id;
    private String name;
    private Integer group;

    public Stu1(Integer id, String name, Integer group) {
        this.id = id;
        this.name = name;
        this.group = group;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Stu1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", group=" + group +
                '}';
    }
}