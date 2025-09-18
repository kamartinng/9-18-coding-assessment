package com.example.teksystemassessment.restAPI.question1;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodOutletService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "https://jsonmock.hackerrank.com/api/food_outlets";

    public List<String> getTopRatedFoodOutlets(String city) {
        int page = 1;
        double maxRating = 0;
        List<String> topOutlets = new ArrayList<>();

        while (true) {
            String url = BASE_URL + "?city=" + city + "&page=" + page;

            ApiResponse response;

            try {
               response = restTemplate.getForObject(url, ApiResponse.class);
            } catch (RestClientException e) {
                throw e;
            }

            if (response == null || response.getData().isEmpty()) break;

            for (FoodOutlet outlet : response.getData()) {
                double rating = outlet.getUserRating().getAverage_rating();
                if (rating > maxRating) {
                    maxRating = rating;
                    topOutlets.clear();
                    topOutlets.add(outlet.getName());
                } else if (rating == maxRating) {
                    topOutlets.add(outlet.getName());
                }
            }
            if (page >= response.getTotal_pages()) {
                break;
            }
            page++;
        }
        return topOutlets;
    }
}
