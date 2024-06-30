package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.params.FilterParam;

import java.time.LocalDateTime;
import java.util.List;

public interface FilterService {
    List<Flight> byDepartureTime(List<Flight> flights, FilterParam filterParam, LocalDateTime departureDateTime);
    List<Flight> byArrivalTime(List<Flight> flights, FilterParam filterParam, LocalDateTime arrivalDateTime);
    List<Flight> byTotalFlightTime(List<Flight> flights, FilterParam filterParam, long hours, long minutes);
    List<Flight> byTotalEarthTime(List<Flight> flights, FilterParam filterParam, long hours, long minutes);
    List<Flight> byTotalSkyTime(List<Flight> flights, FilterParam filterParam, long hours, long minutes);
    List<Flight> byTotalTransfers(List<Flight> flights, FilterParam filterParam, long totalTransfers);
    List<Flight> byTotalSegments(List<Flight> flights, FilterParam filterParam, long totalSegments);

}
