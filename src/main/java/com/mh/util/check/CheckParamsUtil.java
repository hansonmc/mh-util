package com.mh.util.check;

import java.lang.reflect.Field;
import java.util.Date;

public class CheckParamsUtil {

    /**
     * 校验POJO不能为空
     */
    public static String check(Object obj, String keyword) throws IllegalAccessException {
        if(keyword == null){
            throw new RuntimeException("keyword不能为空");
        }
        StringBuilder result = new StringBuilder();
        if(obj == null){
            return null;
        }else{
            Class<?> tClass = obj.getClass();
            Field[] fields = tClass.getDeclaredFields();
            if(fields != null){
                for(Field field : fields){
                    if(field.isAnnotationPresent(CheckParams.class)){
                        CheckParams checkParams = field.getAnnotation(CheckParams.class);
                        field.setAccessible(true);
                        Object value = field.get(obj);
                        String[] keywords = checkParams.keywords();
                        if(containsKey(keyword, keywords)){
                            if(value instanceof String){
                                String strValue = (String) value;
                                if(strValue == null || strValue.length() == 0){
                                    result.append(checkParams.tip()).append(",");
                                }
                            }else if (value == null){
                                result.append(checkParams.tip()).append(",");
                            }
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

    public static boolean containsKey(String keyword, String[] keywords){
        if(keyword == null){
            return false;
        }

        for(String k : keywords ){
            if(keyword.equals(k)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        User user = new User(null, "111", null);

        try {
            String check = CheckParamsUtil.check(user,"add");
            System.out.println(check);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}

class User{
    @CheckParams(keywords = {"add"}, tip = "id不能为NULL")
    private Integer id;

    @CheckParams(keywords = {"add"}, tip = "姓名不能为NULL")
    private String name;

    @CheckParams(keywords = {"add"}, tip = "生日不能为NULL")
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