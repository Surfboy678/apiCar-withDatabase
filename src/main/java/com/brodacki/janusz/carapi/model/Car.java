package com.brodacki.janusz.carapi.model;

public class Car {

    private Integer carId;
    private String name;
    private String mark;
    private String model;
    private String color;
    private int dataProduce;

    public Car() {
    }

    public Car(Integer carId, String name, String mark, String model, String color, int dataProduce) {
        this.carId = carId;
        this.name = name;
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.dataProduce = dataProduce;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDataProduce() {
        return dataProduce;
    }

    public void setDataProduce(int dataProduce) {
        this.dataProduce = dataProduce;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", name='" + name + '\'' +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", dataProduce=" + dataProduce +
                '}';
    }
}
