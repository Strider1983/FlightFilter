package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;

public interface FlightService {
    long totalFlightMinutes (Flight flight);
    long totalTransfers (Flight flight);
    long totalSegments (Flight flight);
    long totalEarthTime (Flight flight);
    long totalSkyTime (Flight flight);
    LocalDateTime departureTime (Flight flight);
    LocalDateTime arrivalTime (Flight flight);
}
