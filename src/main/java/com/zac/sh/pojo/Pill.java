package com.zac.sh.pojo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Pill {
    private int id;
    private String name;

    private int initialQuantity;
    private int doseAmount;
    private List<String> doseTimes;
    private double weight;


    public Pill() {
    }

    public Pill(int id, String name, int initialQuantity, int doseAmount, List<String> doseTimes, double weight) {
        this.id = id;
        this.name = name;
        this.initialQuantity = initialQuantity;
        this.doseAmount = doseAmount;
        this.doseTimes = doseTimes;
        this.weight = weight;
    }

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

    public int getInitialQuantity() {
        return initialQuantity;
    }

    public void setInitialQuantity(int initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

    public int getDoseAmount() {
        return doseAmount;
    }

    public void setDoseAmount(int doseAmount) {
        this.doseAmount = doseAmount;
    }

    public List<String> getDoseTimes() {
        return doseTimes;
    }

    public void setDoseTimesFromJson(String jsonDoseTimes) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 将 JSON 字符串解析为 List<String>
            this.doseTimes = objectMapper.readValue(jsonDoseTimes, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            this.doseTimes = new ArrayList<>();  // 如果解析失败，初始化为空数组
        }
    }

    public void setDoseTimes(Object doseTimesInput) {
        if (doseTimesInput instanceof String) {
            // 如果传入的是 JSON 字符串，调用 setDoseTimesFromJson
            setDoseTimesFromJson((String) doseTimesInput);
        } else if (doseTimesInput instanceof List) {
            // 如果传入的是 List<String>，直接赋值
            this.doseTimes = (List<String>) doseTimesInput;
        } else {
            throw new IllegalArgumentException("Invalid doseTimes format");
        }
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Pill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", initialQuantity=" + initialQuantity +
                ", doseAmount=" + doseAmount +
                ", doseTimes=" + doseTimes +
                ", weight=" + weight +
                '}';
    }
}
