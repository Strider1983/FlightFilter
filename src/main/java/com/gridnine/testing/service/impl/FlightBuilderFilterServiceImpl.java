package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.params.FilterParam;
import com.gridnine.testing.service.FlightBuilderFilterService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FlightBuilderFilterServiceImpl implements FlightBuilderFilterService {
    private final LocalDateTime DEPARTURE_DATE_TIME = LocalDateTime.now();
    private final long MAX_EARTH_HOURS = 2;
    private final long MAX_EARTH_MIUTES = 0;
    private final FilterServiceImpl filterService = new FilterServiceImpl();
    @Override
    public List<Flight> excludeByDepartureTime(List<Flight> flights) {
        return filterService.byDepartureTime(flights, FilterParam.MOREOREQUAL, DEPARTURE_DATE_TIME);
    }

    @Override
    public List<Flight> excludeByIncorrectSegmentDates(List<Flight> flights) {
        List<Flight> filtered = new ArrayList<>();
        for (int i = 0; i < flights.size(); i++) {
            for (int j = 0; j < flights.get(i).getSegments().size(); j++) {
                if (flights.get(i).getSegments().get(j).getArrivalDate().isAfter(flights.get(i).getSegments().get(j).getDepartureDate())) {
                    filtered.add(flights.get(i));

                    }

                }

            }
        return filtered;
    }

    @Override
    public List<Flight> excludeByEarthTotalTime(List<Flight> flights) {
        return filterService.byTotalEarthTime(flights, FilterParam.LESSOREQUAL, MAX_EARTH_HOURS, MAX_EARTH_MIUTES);
    }

    @Override
    public List<Flight> allRulesFilter(List<Flight> flights) {
        List<Flight> filteredByRules = flights;
        List<Flight> filteredByRules1 = excludeByDepartureTime(filteredByRules);
        List<Flight> filteredByRules2 = excludeByIncorrectSegmentDates(filteredByRules1);
        List<Flight> filteredByRules3 = excludeByEarthTotalTime(filteredByRules2);

        return filteredByRules3;
    }
}
