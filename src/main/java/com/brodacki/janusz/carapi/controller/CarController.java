package com.brodacki.janusz.carapi.controller;

import com.brodacki.janusz.carapi.dao.CarDao;
import com.brodacki.janusz.carapi.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private CarDao carDao;

    @Autowired
    public CarController(CarDao carDao) {
        this.carDao = carDao;
    }

    @GetMapping
    public String getAllCars(Model model) {
        List<Car> allCars = carDao.findAll();
        model.addAttribute("cars", allCars);
        return "cars";
    }

    @RequestMapping("/newCar")
    public String createCar(Model model) {
        model.addAttribute("car", new Car());
        return "carform";
    }

    @PostMapping
    public String saveCar(int carId, String name, String mark, String model, String color, int dataProduce) {
        carDao.saveCar(carId, name, mark, model, color, dataProduce);
        return "redirect:/cars";
    }

    @GetMapping("/update")
    public String updateCar(Car newCar, Model model) {
        carDao.updateCar(newCar);
        model.addAttribute("updateCar");
        return "updatecar";
    }

    @GetMapping("/delete/{idCar}")
    public String deleteCar(@PathVariable int idCar, Model model) {
        Car car = carDao.getOne(idCar);
        if(car != null){
            carDao.deleteCar(idCar);
            model.addAttribute("cars", carDao.findAll());
            return "cars";
        }else
            return null;

    }
}
