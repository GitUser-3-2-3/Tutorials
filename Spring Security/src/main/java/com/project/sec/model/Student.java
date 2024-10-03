package com.project.sec.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

    private Integer id;
    private String name;
    private Integer marks;

    public Student(Integer id, String name, Integer marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", marks=" + marks +
            '}';
    }
}
