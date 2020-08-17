package com.brodacki.janusz.carapi.controller;

import com.brodacki.janusz.carapi.service.CarService;
import com.brodacki.janusz.carapi.dao.CarDao;
import com.brodacki.janusz.carapi.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
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
    public List<Car> getAllCars() {
       List<Car> allCars = carService.findAllCars();
        return allCars;
    }

    @RequestMapping("/newCar")
    public String createCar(Model model) {
        model.addAttribute("car", new Car());
        return "carform";
    }

    @PostMapping("/save")
    public void saveCar(@RequestBody Car car) {
        carDao.saveCar(car);
        //return "redirect:/cars";
    }

    @PostMapping("/update")
    public String updateCar(Car newCar, Model model) {
       carService.getUpdateCar(newCar);
        model.addAttribute("updateCar");
        return "updatecar";
    }

    @GetMapping("/delete/{idCar}")
    public String deleteCar(@PathVariable Long idCar, Model model) {
       Car car = carService.getCarById(idCar);
        if(car != null){
          carService.deleteCarById(idCar);
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
