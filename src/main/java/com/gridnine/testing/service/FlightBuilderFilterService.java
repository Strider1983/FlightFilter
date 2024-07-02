package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.util.List;

public interface FlightBuilderFilterService {
    List<Flight> excludeByDepartureTime (List<Flight> flights);
    List<Flight> excludeByIncorrectSegmentDates (List<Flight> flights);
    List<Flight> excludeByEarthTotalTime (List<Flight> flights);
    List<Flight> allRulesBuilderFilter(List<Flight> flights);

}
