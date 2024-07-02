package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;

public class FlightServiceImplTest {
    private final FlightServiceImpl flightService = new FlightServiceImpl();

    private final List<Segment> segments = new ArrayList<>() {{
        add(new Segment(
                LocalDateTime.now().plusDays(2).plusHours(1),
                LocalDateTime.now().plusDays(2).plusHours(3).plusMinutes(10)
        ));
        add(new Segment(
                LocalDateTime.now().plusDays(2).plusHours(4),
                LocalDateTime.now().plusDays(2).plusHours(6)
        ));
        add(new Segment(
                LocalDateTime.now().plusDays(2).plusHours(7),
                LocalDateTime.now().plusDays(2).plusHours(10)
        ));
    }};
    private final Flight flight = new Flight(segments);

    @Test
    public void totalFlightMinutesTest () {
        long expectedResult = 540;
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
        long expectedResult = 110;
        long actualResult = flightService.totalEarthTime(flight);
        Assertions.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void totalSkyTimeTest() {
        long expectedResult = 430;
        long actualResult = flightService.totalSkyTime(flight);
        Assertions.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void departureTimeTest() {
        LocalDateTime expectedResult = LocalDateTime.now().plusDays(2).plusHours(1).truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime actualResult = flightService.departureTime(flight).truncatedTo(ChronoUnit.MINUTES);
        Assertions.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void arrivalTimeTest() {
        LocalDateTime expectedResult = LocalDateTime.now().plusDays(2).plusHours(10).truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime actualResult = flightService.arrivalTime(flight).truncatedTo(ChronoUnit.MINUTES);
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
