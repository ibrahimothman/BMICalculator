package com.dell.bmicalculator;

/**
 * Created by dell on 9/19/2017.
 */

public class InfoModel {
    private int id;
    private String age;
    private String sex;
    private String height;
    private String weight;
    private String bmiResult;


    public InfoModel() {
    }

    public InfoModel(int id, String age, String sex, String height, String weight, String bmiResult) {
        this.id = id;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.bmiResult = bmiResult;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBmiResult() {
        return bmiResult;
    }

    public void setBmiResult(String bmiResult) {
        this.bmiResult = bmiResult;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }


}
