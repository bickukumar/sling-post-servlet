package com.mysite.core.services;

public class Student {
    String name;
    int id, age;
    float marks_obtained;

    public Student(String name, int id, int age, float marks_obtained) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.marks_obtained = marks_obtained;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getMarks_obtained() {
        return marks_obtained;
    }

    public void setMarks_obtained(float marks_obtained) {
        this.marks_obtained = marks_obtained;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                ", marks_obtained=" + marks_obtained +
                '}';
    }
}
