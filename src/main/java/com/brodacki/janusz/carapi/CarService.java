package com.brodacki.janusz.carapi;

import com.brodacki.janusz.carapi.dao.CarDao;
import com.brodacki.janusz.carapi.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private CarDao carDao;

    @Autowired
    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public List<Car> findAllCars(){
        return carDao.findAll();
    }

    public List<Car> findByYear(Long from, Long to) {
        return carDao.findByYear(from, to);
    }

    public Long getMinYear() {
        return carDao.getMinYear();
    }

    public Long getMaxYear() {
        return carDao.getMaxYear();
    }
}