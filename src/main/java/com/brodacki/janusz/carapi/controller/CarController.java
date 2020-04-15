package com.brodacki.janusz.carapi.controller;

import com.brodacki.janusz.carapi.CarService;
import com.brodacki.janusz.carapi.dao.CarDao;
import com.brodacki.janusz.carapi.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private CarDao carDao;

    private CarService carService;

    @Autowired
    public CarController(CarDao carDao, CarService carService) {
        this.carDao = carDao;
        this.carService = carService;
    }

    @GetMapping
    public String getAllCars(Model model) {
       List<Car> allCars = carService.findAllCars();
        model.addAttribute("cars", allCars);
        return "cars";
    }

    @RequestMapping("/newCar")
    public String createCar(Model model) {
        model.addAttribute("car", new Car());
        return "carform";
    }

    @PostMapping
    public String saveCar( Long carId,String name, String mark, String model, String color, Long dataProduce) {
        carDao.saveCar(carId, name, mark, model, color, dataProduce);
        return "redirect:/cars";
    }

    @PostMapping("/update")
    public String updateCar(Car newCar, Model model) {
        carDao.updateCar(newCar);
        model.addAttribute("updateCar");
        return "updatecar";
    }

    @GetMapping("/delete/{idCar}")
    public String deleteCar(@PathVariable Long idCar, Model model) {
        Car car = carDao.getOne(idCar);
        if(car != null){
            carDao.deleteCar(idCar);
            model.addAttribute("cars", carDao.findAll());
            return "cars";
        }else
            return null;

    }
    @GetMapping("/sort")
    public String getSelectedCars(@RequestParam Long from, @RequestParam Long to, Model model) {
        List<Car> cars = carService.findByYear(from, to);
        model.addAttribute("cars", cars);
        model.addAttribute("minYear", carService.getMinYear());
        model.addAttribute("maxYear", carService.getMaxYear());
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        return "sort";
    }

}
