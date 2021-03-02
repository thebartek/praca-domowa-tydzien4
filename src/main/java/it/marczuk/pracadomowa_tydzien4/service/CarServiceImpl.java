package it.marczuk.pracadomowa_tydzien4.service;

import it.marczuk.pracadomowa_tydzien4.entity.Car;
import it.marczuk.pracadomowa_tydzien4.entity.Color;
import it.marczuk.pracadomowa_tydzien4.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private CarRepo repository;

    @Autowired
    public CarServiceImpl(CarRepo repository) {
        this.repository = repository;
    }

    @Override
    public List<Car> getAllCars() {
        return repository.getCarList();
    }

    @Override
    public Car getCarById(Long id) {
        List<Car> carList = getAllCars();
        Optional<Car> first = carList.stream().filter(car -> car.getId() == id).findFirst();
        return first.get();
    }

    @Override
    public List<Car> getCarsByColor(String color) {
        List<Car> carList = getAllCars();
        return carList.stream().filter(car -> car.getColor().toString().equals(color)).collect(Collectors.toList());
    }

    @Override
    public void addCar(Car car) {
        repository.addCar(car);
    }

    @Override
    public Optional<Car> modCar(Car newCar) {
        List<Car> carList = getAllCars();
        Optional<Car> first = carList.stream().filter(car -> car.getId() == newCar.getId()).findFirst();
        repository.modCar(first.get(), newCar);
        return first;
    }

    @Override
    public Optional<Car> modCarParameter(Long id, String modify) {
        String[] mod = modify.split("\"");
        String type = mod[1];
        String modArg = mod[3];
        return updateCarMethod(id, type, modArg);
    }

    @Override
    public Optional<Car> removeCar(Long id) {
        List<Car> carList = getAllCars();
        Optional<Car> first = carList.stream().filter(car -> car.getId() == id).findFirst();
        repository.removeCar(first.get());
        return first;
    }

    private Optional<Car> updateCarMethod(long id, String type, String modArg){
        List<Car> carList = getAllCars();
        Optional<Car> first = carList.stream().filter(car -> car.getId() == id).findFirst();
        if(first.isPresent()) {
            if(type.equals("mark")){
                first.get().setMark(modArg);
            }
            if(type.equals("model")){
                first.get().setModel(modArg);
            }
            if(type.equals("color")){
                first.get().setColor(Color.valueOf(modArg));
            }
            return first;
        }
        return Optional.empty();
    }
}
