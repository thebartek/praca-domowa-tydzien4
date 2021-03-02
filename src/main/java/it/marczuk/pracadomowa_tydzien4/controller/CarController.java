package it.marczuk.pracadomowa_tydzien4.controller;

import it.marczuk.pracadomowa_tydzien4.entity.Car;
import it.marczuk.pracadomowa_tydzien4.entity.PomId;
import it.marczuk.pracadomowa_tydzien4.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {
    private CarService carService;

    private List<Car> carList2 = new ArrayList<>();

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String getCar(Model model) {
        List<Car> carList = carService.getAllCars();
        model.addAttribute("newCar", new Car());
        model.addAttribute("sendCar", new PomId());
        if(!carList2.isEmpty()) {
            model.addAttribute("cars", carList2);
        } else {
            model.addAttribute("cars", carList);
        }
        return "index";
    }

    @PostMapping("/id/send")
    public String getCarByIdPost(@RequestParam(name = "id") Long id) {
        if(!carList2.isEmpty()) {
            carList2.remove(0);
        }
        carList2.add(carService.getCarById(id));
        return "redirect:/cars";
    }

    @GetMapping("/seeall")
    public String getAllCars(){
        if(!carList2.isEmpty()) {
            carList2.remove(0);
        }
        return "redirect:/cars";
    }

    @GetMapping("/add")
    public String addCarFormSite(Model model) {
        model.addAttribute("newCar", new Car());
        return "add";
    }

    @PostMapping("/addcar")
    public String addCar(@ModelAttribute Car car) {
        carService.addCar(car);
        return "redirect:/cars";
    }

    @PostMapping("/edit/{id}")
    public String modCar(@PathVariable long id, @ModelAttribute Car car) {
        car.setId(id);
        carService.modCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/remove/{id}")
    public String removeCar(@PathVariable Long id) {
        carService.removeCar(id);
        return "redirect:/cars";
    }
}
