package com.brodacki.janusz.carapi.dao;

import com.brodacki.janusz.carapi.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CarDaoImpl implements CarDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveCar(int carId, String name, String mark, String model, String color, int dataProduce) {
        Car car = new Car(carId, name, mark, model, color, dataProduce);
        String sql = "INSERT INTO cars VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, car.getCarId(), car.getName(), car.getMark(), car.getModel(), car.getColor(), car.getDataProduce());
    }

    @Override
    public List<Car> findAll() {
        String sql = "SELECT * FROM cars";
        List<Car> listCars = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        maps.stream().forEach(element -> listCars.add(new Car(
                Integer.parseInt(String.valueOf(element.get("car_id"))),
                String.valueOf(element.get("name")),
                String.valueOf(element.get("mark")),
                String.valueOf(element.get("model")),
                String.valueOf(element.get("color")),
                Integer.parseInt(String.valueOf(element.get("data_produce")
                ))
        )));
        return listCars;
    }

    @Override
    public void updateCar(Car newCar) {
        String sql ="UPDATE cars Set cars.data_produce = ?, cars.color =?, cars.model =?, cars.mark, cars.name =? WHERE cars.car_id =?";
        jdbcTemplate.update(sql, newCar.getDataProduce(), newCar.getColor(), newCar.getModel(), newCar.getMark(), newCar.getName(), newCar.getCarId());
    }

    public Car getOne(int idCar){
        String sql = "SELECT * FROM cars WHERE car_id = ? ";
        return jdbcTemplate.queryForObject(
                sql, (resultSet, i) -> new Car(
                resultSet.getInt("car_id"),
                resultSet.getString("name"),
                resultSet.getString("mark"),
                resultSet.getString("model"),
                resultSet.getString("color"),
                resultSet.getInt("data_produce")), idCar);
    }

    @Override
    public void deleteCar(Integer idCar) {
        String sql = "DELETE FROM cars WHERE car_id = ? ";
        jdbcTemplate.update(sql, idCar);

    }

}
