package com.dc.spring.demo.vo;

/**
 * @Description
 * @Author DC
 * @Date 2021-03-30
 */
public class Student {
    private int id;
    String name;
    protected boolean sex;
    public float score;

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

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
