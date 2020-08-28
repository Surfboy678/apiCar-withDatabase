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
    public void saveCar(Car car) {
        //Car car = new Car(carId, name, mark, model, color, dataProduce);
        String sql = "INSERT INTO cars VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, car.getCarId(), car.getName(), car.getMark(), car.getModel(), car.getColor(), car.getDataProduce());
    }

    @Override
    public List<Car> findAll() {
        String sql = "SELECT * FROM cars";
        List<Car> listCars = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        maps.stream().forEach(element -> listCars.add(new Car(
                Long.parseLong(String.valueOf(element.get("car_id"))),
                String.valueOf(element.get("name")),
                String.valueOf(element.get("mark")),
                String.valueOf(element.get("model")),
                String.valueOf(element.get("color")),
                Long.parseLong(String.valueOf(element.get("data_produce")
                ))
        )));
        return listCars;
    }

    @Override
    public void updateCar(Car newCar) {
        String sql = "UPDATE cars Set cars.data_produce = ?, cars.color =?, cars.model =?, cars.mark =?, cars.name =? WHERE cars.car_id =?";
        jdbcTemplate.update(sql, newCar.getDataProduce(), newCar.getColor(), newCar.getModel(), newCar.getMark(), newCar.getName(), newCar.getCarId());
    }

    public Car getOne(Long idCar) {
        String sql = "SELECT * FROM cars WHERE car_id = ? ";
        return jdbcTemplate.queryForObject(
                sql, (resultSet, i) -> new Car(
                        resultSet.getLong("car_id"),
                        resultSet.getString("name"),
                        resultSet.getString("mark"),
                        resultSet.getString("model"),
                        resultSet.getString("color"),
                        resultSet.getLong("data_produce")), idCar);
    }


    @Override
    public void deleteCar(Long idCar) {
        String sql = "DELETE FROM cars WHERE car_id = ? ";
        jdbcTemplate.update(sql, idCar);

    }

    @Override
    public List<Car> findByYear(Long from, Long to) {
        String sql = "SELECT * FROM cars " +
                " WHERE data_produce between ? and ?";
        List<Car> listCars = new ArrayList<>();
        List<Map<String, Object>> mapCars = jdbcTemplate.queryForList(sql, from, to);
        addCars(listCars, mapCars);
        return listCars;
    }

    public void addCars(List<Car> carList, List<Map<String, Object>> carMaps) {
        carMaps.stream().forEach(element -> carList.add(new Car(
                Long.parseLong(String.valueOf(element.get("car_id"))),
                String.valueOf(element.get("name")),
                String.valueOf(element.get("mark")),
                String.valueOf(element.get("model")),
                String.valueOf(element.get("color")),
                Long.parseLong(String.valueOf(element.get("data_produce")
                ))
        )));
    }

    @Override
    public Long getMinYear() {
        String sql = "select min(data_produce) from cars";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public Long getMaxYear() {
        String sql = "select max(data_produce) from cars";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

}
