package com.example.teksystemassessment.restAPI.question1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/food-outlets")
public class FoodOutletController {

    private final FoodOutletService foodOutletService;

    public FoodOutletController(FoodOutletService foodOutletService) {
        this.foodOutletService = foodOutletService;
    }

    @GetMapping("top-rated")
    public ResponseEntity<List<String>> getTopRated(@RequestParam String city) {
        List<String> names = foodOutletService.getTopRatedFoodOutlets(city);
        return ResponseEntity.ok(names);
    }
}
