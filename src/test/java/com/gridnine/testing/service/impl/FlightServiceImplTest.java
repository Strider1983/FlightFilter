package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;

public class FlightServiceImplTest {
    FlightServiceImpl flightService = new FlightServiceImpl();

    @Test
    public void totalFlightMinutesTest () {
        Segment segment = new Segment(
                LocalDateTime.of(2024,06,30,15,00),
                LocalDateTime.of(2024, 06, 30, 16, 15 ));
        List<Segment> segments = new ArrayList<>();
        segments.add(segment);
        Flight flight = new Flight(segments);

        long expectedResult = 75;
        long actualResult = flightService.totalFlightMinutes(flight);
        Assertions.assertEquals(expectedResult, actualResult);
    }


}
