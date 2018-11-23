package com.mh.util.check;

import java.lang.reflect.Field;
import java.util.Date;

public class CheckNotNullUtil {

    /**
     * 校验POJO不能为空
     */
    public static String check(Object obj) throws IllegalAccessException {
        StringBuilder result = new StringBuilder();
        if(obj == null){
            return null;
        }else{
            Class<?> tClass = obj.getClass();
            Field[] fields = tClass.getDeclaredFields();
            if(fields != null){
                for(Field field : fields){
                    if(field.isAnnotationPresent(CheckNotNull.class)){
                        CheckNotNull annotation = field.getAnnotation(CheckNotNull.class);
                        field.setAccessible(true);
                        Object value = field.get(obj);
                        if(value instanceof String){
                            String strValue = (String) value;
                            if(strValue == null || strValue.length() == 0){
                                result.append(annotation.value()).append(",");
                            }
                        }else if (value == null){
                            result.append(annotation.value()).append(",");
                        }
                        field.setAccessible(false);
                    }
                }

                if(result.length() > 0){
                    result.delete(result.length()-1, result.length());
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        User user = new User(null, "", null);

        try {
            String check = CheckNotNullUtil.check(user);
            System.out.println(check);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}

class User{
    @CheckNotNull(value = "id不能为NULL")
    private Integer id;

    @CheckNotNull(value = "姓名不能为NULL")
    private String name;

    @CheckNotNull(value = "生日不能为NULL")
    private Date birthday;


    public User(Integer id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}