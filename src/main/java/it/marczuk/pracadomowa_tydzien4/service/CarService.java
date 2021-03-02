package it.marczuk.pracadomowa_tydzien4.service;

import it.marczuk.pracadomowa_tydzien4.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getAllCars();

    Car getCarById(Long id);

    List<Car> getCarsByColor(String color);

    void addCar(Car car);

    Optional<Car> modCar(Car car);

    Optional<Car> modCarParameter(Long id, String modify);

    Optional<Car> removeCar(Long id);
}
