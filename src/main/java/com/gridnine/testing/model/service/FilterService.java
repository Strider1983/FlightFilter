package com.gridnine.testing.model.service;

import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FilterService {
    List<Flight> byDepartureTime(List<Flight> flights, FilterParam departureDateTime, LocalDateTime departureFilterTime);
}
