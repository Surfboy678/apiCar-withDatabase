package com.brodacki.janusz.carapi.dao;

import com.brodacki.janusz.carapi.model.Car;

import java.util.List;

public interface CarDao {

    void saveCar( Long carId, String name, String mark, String model, String color, Long dataProduce);

    List<Car> findAll();

    void updateCar(Car newCar);

    void deleteCar(Long idCar);

    Car getOne(Long idCar);

    List<Car> findByYear(Long from, Long to);

    Long getMinYear();

    Long getMaxYear();





}
