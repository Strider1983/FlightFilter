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
    private final long MIN_TOTAL_FLIGHT_MINUTES = 120;
    private final long MIN_TOTAL_EARTH_MINUTES = 0;
    private final long MIN_TOTAL_SKY_MINUTES = 120;
    private final long MIN_NUMBER_OF_TRANSFERS = 0;
    private final long MIN_NUMBER_OF_SEGMENTS = 1;

    /**
     * Filters flights by departure DateTime before, after or equal to specified date-time
     * @param flights
     * @param filterParam
     * @param departureDateTime
     * @return List<Flight>
     */

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

    /**
     * Filters flights by arrival Date-Time before, after or equal to specified date-time
     * @param flights
     * @param filterParam
     * @param arrivalDateTime
     * @return List<Flight>
     */

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

    /**
     * Filters flights by  total time from departure to arrival before, after or equal to specified time
     * @param flights
     * @param filterParam
     * @param hours
     * @param minutes
     * @return List<Flight>
     */

    @Override
    public List<Flight> byTotalFlightTime(List<Flight> flights, FilterParam filterParam, long hours, long minutes) {
        long totalFlightMinutes = hours * 60 + minutes;
        if (totalFlightMinutes < MIN_TOTAL_FLIGHT_MINUTES) {
            throw new FilterParamExeption("Total flight time can't be less than" + MIN_TOTAL_FLIGHT_MINUTES/60 + " hours");
        }
        List<Flight> filteredByTotalTime = new ArrayList<>();
        if (filterParam.equals(FilterParam.LESSOREQUAL)) {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.totalFlightMinutes(flights.get(i)) <= totalFlightMinutes) {
                    filteredByTotalTime.add(flights.get(i));
                }
            }
        } else if (filterParam.equals(FilterParam.MOREOREQUAL)) {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.totalFlightMinutes(flights.get(i)) >= totalFlightMinutes){
                    filteredByTotalTime.add(flights.get(i));
                }
            }
        } else {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.totalFlightMinutes(flights.get(i)) == totalFlightMinutes){
                    filteredByTotalTime.add(flights.get(i));
                }
            }
        }
        return filteredByTotalTime;
    }

    /**
     * Filters flights by total time on earth between segments before, after or equal to specified time
     * @param flights
     * @param filterParam
     * @param hours
     * @param minutes
     * @return List<Flight>
     */

    @Override
    public List<Flight> byTotalEarthTime(List<Flight> flights, FilterParam filterParam, long hours, long minutes) {
        long totalEarthMinutes = hours * 60 + minutes;
        if (totalEarthMinutes < MIN_TOTAL_EARTH_MINUTES) {
            throw new FilterParamExeption("Total time on earth can't be less than" + MIN_TOTAL_EARTH_MINUTES/60 + "hours");
        }
        List<Flight> filteredByTotalEarthTime = new ArrayList<>();
        if (filterParam.equals(FilterParam.LESSOREQUAL)) {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.totalEarthTime(flights.get(i)) <= totalEarthMinutes) {
                    filteredByTotalEarthTime.add(flights.get(i));
                }
            }
        } else if (filterParam.equals(FilterParam.MOREOREQUAL)) {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.totalEarthTime(flights.get(i)) >= totalEarthMinutes){
                    filteredByTotalEarthTime.add(flights.get(i));
                }
            }
        } else {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.totalEarthTime(flights.get(i)) == totalEarthMinutes){
                    filteredByTotalEarthTime.add(flights.get(i));
                }
            }
        }
        return filteredByTotalEarthTime;
    }

    /**
     * Filters flights by total time in the sky of all flight before, after or equal to specified time
     * @param flights
     * @param filterParam
     * @param hours
     * @param minutes
     * @return List<Flight>
     */

    @Override
    public List<Flight> byTotalSkyTime(List<Flight> flights, FilterParam filterParam, long hours, long minutes) {
        long totalSkyMinutes = hours * 60 + minutes;
        if (totalSkyMinutes < MIN_TOTAL_SKY_MINUTES) {
            throw new FilterParamExeption("Total time in the sky can't be less than" + MIN_TOTAL_SKY_MINUTES/60 + "hours");
        }
        List<Flight> filteredByTotalSkyTime = new ArrayList<>();
        if (filterParam.equals(FilterParam.LESSOREQUAL)) {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.totalSkyTime(flights.get(i)) <= totalSkyMinutes) {
                    filteredByTotalSkyTime.add(flights.get(i));
                }
            }
        } else if (filterParam.equals(FilterParam.MOREOREQUAL)) {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.totalSkyTime(flights.get(i)) >= totalSkyMinutes){
                    filteredByTotalSkyTime.add(flights.get(i));
                }
            }
        } else {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.totalSkyTime(flights.get(i)) == totalSkyMinutes){
                    filteredByTotalSkyTime.add(flights.get(i));
                }
            }
        }
        return filteredByTotalSkyTime;
    }

    /**
     * Filters flights by total time number of transfers between flight segments before, after or equal to specified value
     * @param flights
     * @param filterParam
     * @param totalTransfers
     * @return List<Flight>
     */

    @Override
    public List<Flight> byTotalTransfers(List<Flight> flights, FilterParam filterParam, long totalTransfers) {
        if (totalTransfers < MIN_NUMBER_OF_TRANSFERS) {
            throw new FilterParamExeption("Number of transfers can't be less than " + MIN_NUMBER_OF_TRANSFERS);
        }
        List<Flight> filteredByTotalTransfers = new ArrayList<>();
        if (filterParam.equals(FilterParam.LESSOREQUAL)) {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.totalTransfers(flights.get(i)) <= totalTransfers) {
                    filteredByTotalTransfers.add(flights.get(i));
                }
            }
        } else if (filterParam.equals(FilterParam.MOREOREQUAL)) {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.totalTransfers(flights.get(i)) >= totalTransfers) {
                    filteredByTotalTransfers.add(flights.get(i));
                }
            }
        } else {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.totalTransfers(flights.get(i)) == totalTransfers) {
                    filteredByTotalTransfers.add(flights.get(i));
                }
            }
        }
        return filteredByTotalTransfers;
    }

    /**
     * Filters flights by total time number of flight segments before, after or equal to specified value
     * @param flights
     * @param filterParam
     * @param totalSegments
     * @return List<Flight>
     */

    @Override
    public List<Flight> byTotalSegments(List<Flight> flights, FilterParam filterParam, long totalSegments) {
        if (totalSegments < MIN_NUMBER_OF_SEGMENTS) {
            throw new FilterParamExeption("Number of segments can't be less than " + MIN_NUMBER_OF_SEGMENTS);
        }
        List<Flight> filteredByTotalSegments = new ArrayList<>();
        if (filterParam.equals(FilterParam.LESSOREQUAL)) {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.totalSegments(flights.get(i)) <= totalSegments) {
                    filteredByTotalSegments.add(flights.get(i));
                }
            }
        } else if (filterParam.equals(FilterParam.MOREOREQUAL)) {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.totalSegments(flights.get(i)) >= totalSegments) {
                    filteredByTotalSegments.add(flights.get(i));
                }
            }
        } else {
            for (int i = 0; i < flights.size(); i++) {
                if (flightService.totalSegments(flights.get(i)) == totalSegments) {
                    filteredByTotalSegments.add(flights.get(i));
                }
            }
        }
        return filteredByTotalSegments;
    }
}
