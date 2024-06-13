package com.google.homework2;


public class Student {

    private static Long idCounter = 1L;
    private Long id;
    private String name;
    private String groupName;

    public Student(String name, String groupName) {
        this.id = idCounter++;
        this.name = name;
        this.groupName = groupName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
