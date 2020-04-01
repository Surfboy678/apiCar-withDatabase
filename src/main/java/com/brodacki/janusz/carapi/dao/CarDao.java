package com.brodacki.janusz.carapi.dao;

import com.brodacki.janusz.carapi.model.Car;

import java.util.List;

public interface CarDao {

    void saveCar(int carId, String name, String mark, String model, String color, int dataProduce);

    List<Car> findAll();

    void updateCar(Car newCar);

    void deleteCar(Integer idCar);

    Car getOne(int idCar);



}
