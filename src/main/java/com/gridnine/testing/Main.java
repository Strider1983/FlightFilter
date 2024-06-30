package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.params.FilterParam;
import com.gridnine.testing.service.FlightBuilder;
import com.gridnine.testing.service.impl.FilterServiceImpl;
import com.gridnine.testing.service.impl.FlightServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Flight Filter!");
        FlightServiceImpl flightService = new FlightServiceImpl();
        FilterServiceImpl filterService = new FilterServiceImpl();

        FlightBuilder flightBuilder = new FlightBuilder();
        List<Flight> flights = flightBuilder.createFlights();

        List<Flight> filterFlights = filterService.byDepartureTime(flights, FilterParam.BEFORE, LocalDateTime.now());
        System.out.println(filterFlights);

    }
}