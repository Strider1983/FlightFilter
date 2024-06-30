package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.params.FilterParam;
import com.gridnine.testing.service.FlightBuilder;
import com.gridnine.testing.service.impl.FilterServiceImpl;
import com.gridnine.testing.service.impl.FlightBuilderFilterServiceImpl;
import com.gridnine.testing.service.impl.FlightServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Flight Filter!");
        FlightServiceImpl flightService = new FlightServiceImpl();
        FilterServiceImpl filterService = new FilterServiceImpl();
        FlightBuilderFilterServiceImpl flightBuilderFilterService = new FlightBuilderFilterServiceImpl();

        FlightBuilder flightBuilder = new FlightBuilder();
        List<Flight> flights = flightBuilder.createFlights();

        List<Flight> flights2 = flightBuilderFilterService.excludeByIncorrectSegmentDates(flights);
        for (int i = 0; i < flights2.size(); i++) {
            System.out.println(flights2.get(i));
        }



    }
}