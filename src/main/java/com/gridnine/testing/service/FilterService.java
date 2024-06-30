package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FilterService {
    List<Flight> byDepartureTime(List<Flight> flights, FilterParam departureDateTime, LocalDateTime departureFilterTime);
}
