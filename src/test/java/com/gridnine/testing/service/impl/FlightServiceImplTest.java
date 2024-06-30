package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;

public class FlightServiceImplTest {
    private final FlightServiceImpl flightService = new FlightServiceImpl();

    private final List<Segment> segments = new ArrayList<>() {{
        add(new Segment(
                LocalDateTime.of(2024,06,30,15,00),
                LocalDateTime.of(2024, 06, 30, 16, 15 ))
        );
        add(new Segment(
                LocalDateTime.of(2024,06,30,17,30),
                LocalDateTime.of(2024, 06, 30, 18, 45 ))
        );
        add(new Segment(
                LocalDateTime.of(2024,06,30,19,30),
                LocalDateTime.of(2024, 06, 30, 21, 10 ))
        );
    }};
    private final Flight flight = new Flight(segments);

    @Test
    public void totalFlightMinutesTest () {
        long expectedResult = 370;
        long actualResult = flightService.totalFlightMinutes(flight);
        Assertions.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void totalTransfersTest () {
        long expectedResult = 2;
        long actualResult = flightService.totalTransfers(flight);
        Assertions.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void totalSegmentsTest() {
        long expectedResult = 3;
        long actualResult = flightService.totalSegments(flight);
        Assertions.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void totalEarthTimeTest() {
        long expectedResult = 120;
        long actualResult = flightService.totalEarthTime(flight);
        Assertions.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void totalSkyTimeTest() {
        long expectedResult = 250;
        long actualResult = flightService.totalSkyTime(flight);
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
