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
    private final LocalDateTime EARLIEST_DEPARTURE_TIME = LocalDateTime.now();
    private final LocalDateTime EARLIEST_ARRIVAL_TIME = LocalDateTime.now().plusHours(2);

    @Override
    public List<Flight> byDepartureTime(List<Flight> flights, FilterParam filterParam, LocalDateTime departureDateTime) {
        if (departureDateTime.isBefore(EARLIEST_DEPARTURE_TIME)) {
            throw new FilterParamExeption("Departure date-time cannot be before " + EARLIEST_DEPARTURE_TIME);
        }
        List<Flight> filteredByDepartureTime = new ArrayList<>();
        if (filterParam.equals(FilterParam.LESSOREQUAL)) {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.departureTime(flights.get(i)).isBefore(departureDateTime) || flightService.departureTime(flights.get(i)).isEqual(departureDateTime)) {
                    filteredByDepartureTime.add(flights.get(i));
                }
            }
        } else if (filterParam.equals(FilterParam.MOREOREQUAL)) {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.departureTime(flights.get(i)).isAfter(departureDateTime) || flightService.departureTime(flights.get(i)).isEqual(departureDateTime)) {
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

    @Override
    public List<Flight> byArrivalTime(List<Flight> flights, FilterParam filterParam, LocalDateTime arrivalDateTime) {

        if (arrivalDateTime.isBefore(EARLIEST_ARRIVAL_TIME)) {
            throw new FilterParamExeption("Arrival date-time cannot be before " + EARLIEST_ARRIVAL_TIME);
        }
        List<Flight> filteredByArrivalTime = new ArrayList<>();
        if (filterParam.equals(FilterParam.LESSOREQUAL)) {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.arrivalTime(flights.get(i)).isBefore(arrivalDateTime) || flightService.arrivalTime(flights.get(i)).isEqual(arrivalDateTime)) {
                    filteredByArrivalTime.add(flights.get(i));
                }
            }
        } else if (filterParam.equals(FilterParam.MOREOREQUAL)) {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.arrivalTime(flights.get(i)).isAfter(arrivalDateTime) || flightService.arrivalTime(flights.get(i)).isEqual(arrivalDateTime)) {
                    filteredByArrivalTime.add(flights.get(i));
                }
            }
        } else {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.arrivalTime(flights.get(i)).isEqual(arrivalDateTime)) {
                    filteredByArrivalTime.add(flights.get(i));
                }
            }
        }

        return filteredByArrivalTime;
    }

    @Override
    public List<Flight> byTotalFlightTime(List<Flight> flights, FilterParam filterParam, long hours, long minutes) {
        return null;
    }

    @Override
    public List<Flight> byTotalEarthTime(List<Flight> flights, FilterParam filterParam, long hours, long minutes) {
        return null;
    }

    @Override
    public List<Flight> byTotalSkyTime(List<Flight> flights, FilterParam filterParam, long hours, long minutes) {
        return null;
    }

    @Override
    public List<Flight> byTotalTransfers(List<Flight> flights, FilterParam filterParam, long totalTransfers) {
        return null;
    }

    @Override
    public List<Flight> byTotalSegments(List<Flight> flights, FilterParam filterParam, long totalTransfers) {
        return null;
    }
}
