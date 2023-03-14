package com.code.pojo;

import java.util.Objects;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/2 16:02
 * @email 3349495429@qq.com
 * @className com.code.pojo.Student
 * @description:
 */
public class Student implements Comparable{
    private int id;
    private String name;
    private int age;
    private int sex;
    private String address;

    public Student(int id, String name, int age, int sex, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
    }
    public Student(){};

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && age == student.age && sex == student.sex && Objects.equals(name, student.name) && Objects.equals(address, student.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, sex, address);
    }

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof Student)){
            return -1;
        }
        int res;
        Student newStudent = (Student)o;
        res = this.getId()-newStudent.getId();
        res = res!=0?res:this.getName().compareTo(newStudent.getName());
        res = res!=0?res:this.getAge()-newStudent.getAge();
        res = res!=0?res:this.getSex()-newStudent.getSex();
        res = res!=0?res:this.getAddress().compareTo(newStudent.getAddress());
        return res;
    }
}
