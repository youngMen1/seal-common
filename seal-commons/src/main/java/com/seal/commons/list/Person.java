package com.seal.commons.list;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/23 16:52
 **/
public class Person {
    private int id;
    private String sname;
    private int age;

    public Person() {
    }

    public Person(int id, String sname, int age) {
        this.id = id;
        this.sname = sname;
        this.age = age;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getSname() {
        return sname;
    }


    public void setSname(String sname) {
        this.sname = sname;
    }


    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 当我们将自己写的类存入set集合时一定要重写 equals和hashCode
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if (obj != null && obj instanceof Person) {
            Person o = (Person) obj;
            if (this.id == o.id && this.age == o.age) {
                if (sname != null) {
                    return sname.equals(o.sname);
                } else {
                    return o.sname == null;
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return this.id + this.age;
    }

}
