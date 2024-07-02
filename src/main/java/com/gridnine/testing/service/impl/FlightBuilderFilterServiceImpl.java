package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.params.FilterParam;
import com.gridnine.testing.service.FlightBuilderFilterService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class FlightBuilderFilterServiceImpl implements FlightBuilderFilterService {
    private final LocalDateTime DEPARTURE_DATE_TIME = LocalDateTime.now();
    private final long MAX_EARTH_HOURS = 2;
    private final long MAX_EARTH_MIUTES = 0;
    private final FilterServiceImpl filterService = new FilterServiceImpl();

    /**
     * excludes flights from list witch departure before present date-time
     * @param flights
     * @return List<Flight>
     */
    @Override
    public List<Flight> excludeByDepartureTime(List<Flight> flights) {
        return filterService.byDepartureTime(flights, FilterParam.MOREOREQUAL, DEPARTURE_DATE_TIME);
    }

    /**
     * excludes flights including segments witch departure before arrival
     * @param flights
     * @return List<Flight>
     */

    @Override
    public List<Flight> excludeByIncorrectSegmentDates(List<Flight> flights) {
        List<Flight> filtered = new ArrayList<>();
        for (int i = 0; i < flights.size(); i++) {
            int temp = 0;
            for (int j = 0; j < flights.get(i).getSegments().size(); j++) {
                if (flights.get(i).getSegments().get(j).getArrivalDate().isBefore(flights.get(i).getSegments().get(j).getDepartureDate())) {
                    temp++;
                }
            }
            if (temp == 0) {
                filtered.add(flights.get(i));
            }
        }
        return filtered;
    }

    /**
     * excludes flights with stay on earth more than specified time
     * @param flights
     * @return List<Flight>
     */

    @Override
    public List<Flight> excludeByEarthTotalTime(List<Flight> flights) {
        return filterService.byTotalEarthTime(flights, FilterParam.LESSOREQUAL, MAX_EARTH_HOURS, MAX_EARTH_MIUTES);
    }

    @Override
    public List<Flight> allRulesBuilderFilter(List<Flight> flights) {
        List<Flight> filteredByRules = flights;
        List<Flight> filteredByRules1 = excludeByDepartureTime(filteredByRules);
        List<Flight> filteredByRules2 = excludeByIncorrectSegmentDates(filteredByRules1);
        List<Flight> filteredByRules3 = excludeByEarthTotalTime(filteredByRules2);

        return filteredByRules3;
    }
}
