package com.gridnine.testing.service.impl;

import com.gridnine.testing.exeption.FilterParamExeption;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.params.FilterParam;
import com.gridnine.testing.service.FilterService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterServiceImpl implements FilterService {
    FlightServiceImpl flightService = new FlightServiceImpl();

    @Override
    public List<Flight> byDepartureTime(List<Flight> flights, FilterParam filterParam, LocalDateTime departureDateTime) {
        if (departureDateTime.isBefore(LocalDateTime.now())) {
            throw new FilterParamExeption("Departure date-time cannot be before present date-time");
        }
        List<Flight> filteredByDepartureTime = new ArrayList<>();
        if (filterParam.equals(FilterParam.LESSOREQUAL)) {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.departureTime(flights.get(i)).isBefore(departureDateTime)) {
                    filteredByDepartureTime.add(flights.get(i));
                }
            }
        } else if (filterParam.equals(FilterParam.MOREOREQUAL)) {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.departureTime(flights.get(i)).isAfter(departureDateTime)) {
                    filteredByDepartureTime.add(flights.get(i));
                }
            }
        } else {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.departureTime(flights.get(i)).isEqual(departureDateTime)) {
                    filteredByDepartureTime.add(flights.get(i));
                }
            }
        }

        return filteredByDepartureTime;
    }
}
