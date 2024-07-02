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

        FilterServiceImpl filterService = new FilterServiceImpl();
        FlightBuilderFilterServiceImpl flightBuilderFilterService = new FlightBuilderFilterServiceImpl();

        FlightBuilder flightBuilder = new FlightBuilder();
        List<Flight> flights = flightBuilder.createFlights();
        List<Flight> flightsFilteredByBuilderRules = flightBuilderFilterService.allRulesBuilderFilter(flights);

        List<Flight> flightsFilteredByDeparture = filterService.byDepartureTime(flights, FilterParam.MOREOREQUAL, LocalDateTime.now().plusDays(3).plusHours(7));
        List<Flight> flightsFilteredByArrival = filterService.byArrivalTime(flights, FilterParam.LESSOREQUAL, LocalDateTime.now().plusDays(3).plusHours(5));
        List<Flight> flightsFilteredByTotalFlightTime = filterService.byTotalFlightTime(flights, FilterParam.MOREOREQUAL, 4,10);
        List<Flight> flightsFilteredByTotalEarthTime = filterService.byTotalEarthTime(flights, FilterParam.LESSOREQUAL, 1,45);
        List<Flight> flightsFilteredByTotalSkyTime = filterService.byTotalSkyTime(flights, FilterParam.MOREOREQUAL, 3,15);
        List<Flight> flightsFilteredByTotalTransfers = filterService.byTotalTransfers(flights, FilterParam.LESSOREQUAL, 3);
        List<Flight> flightsFilteredByTotalSegments = filterService.byTotalSegments(flights, FilterParam.MOREOREQUAL, 2);






    }
}