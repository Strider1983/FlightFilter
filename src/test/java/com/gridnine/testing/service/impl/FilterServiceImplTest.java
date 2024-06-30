package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.model.params.FilterParam;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class FilterServiceImplTest {
    @InjectMocks
    FilterServiceImpl filterService = new FilterServiceImpl();
    @Mock
    FlightServiceImpl flightService = new FlightServiceImpl();
    private final Flight flight1 = new Flight(new ArrayList<Segment>() {{
        add(new Segment(
                        LocalDateTime.of(2024,06,30,15,00),
                        LocalDateTime.of(2024, 06, 30, 16, 15)
                )
        );
        add(new Segment(
                        LocalDateTime.of(2024,06,30,17,30),
                        LocalDateTime.of(2024, 06, 30, 18, 45)
                )
        );
        add(new Segment(
                        LocalDateTime.of(2024,06,30,19,30),
                        LocalDateTime.of(2024, 06, 30, 21, 10)
                )
        );
    }});
    private final Flight flight2 = new Flight(new ArrayList<Segment>() {{
        add(new Segment(
                        LocalDateTime.of(2024,07,02,10,00),
                        LocalDateTime.of(2024, 07, 02, 13, 30)
                )
        );
        add(new Segment(
                        LocalDateTime.of(2024,07,02,14,00),
                        LocalDateTime.of(2024, 07, 02, 17, 00)
                )
        );
        add(new Segment(
                        LocalDateTime.of(2024,07,02,17,30),
                        LocalDateTime.of(2024, 07, 02, 20, 00)
                )
        );
    }});
    private final Flight flight3 = new Flight(new ArrayList<Segment>() {{
        add(new Segment(
                        LocalDateTime.of(2024,07,07,10,30),
                        LocalDateTime.of(2024, 07, 07, 11, 30)
                )
        );
        add(new Segment(
                        LocalDateTime.of(2024,07,07,12,00),
                        LocalDateTime.of(2024, 07, 07, 14, 00)
                )
        );
        add(new Segment(
                        LocalDateTime.of(2024,07,07,14,45),
                        LocalDateTime.of(2024, 07, 07, 17, 45)
                )
        );
    }});
    private final List<Flight> flights = new ArrayList<>() {{
        add(flight1);
        add(flight2);
        add(flight3);
    }};
    @Test
    public void byDepartureTimeTest() {
        LocalDateTime depTime = LocalDateTime.of(2024,07,02,10,00);
        List<Flight> expectedResultLessOrEqual = new ArrayList<>() {{
            add(flight1);
            add(flight2);
        }};
        List<Flight> expectedResultMoreOrEqual = new ArrayList<>() {{
            add(flight2);
            add(flight3);
        }};
        List<Flight> expectedResultEqual = new ArrayList<>() {{
            add(flight2);
        }};
        List<Flight> actualResultLessOrEqual = filterService.byDepartureTime(flights, FilterParam.LESSOREQUAL, depTime);
        List<Flight> actualResultMoreOrEqual = filterService.byDepartureTime(flights, FilterParam.MOREOREQUAL, depTime);
        List<Flight> actualResultEqual = filterService.byDepartureTime(flights, FilterParam.EQUAL, depTime);
        Assertions.assertEquals(expectedResultLessOrEqual, actualResultLessOrEqual);
        Assertions.assertEquals(expectedResultMoreOrEqual, actualResultMoreOrEqual);
        Assertions.assertEquals(expectedResultEqual, actualResultEqual);

    }
    @Test
    public void byArrivalTimeTest() {
        LocalDateTime depTime = LocalDateTime.of(2024, 07, 02, 20, 00);
        List<Flight> expectedResultLessOrEqual = new ArrayList<>() {{
            add(flight1);
            add(flight2);
        }};
        List<Flight> expectedResultMoreOrEqual = new ArrayList<>() {{
            add(flight2);
            add(flight3);
        }};
        List<Flight> expectedResultEqual = new ArrayList<>() {{
            add(flight2);
        }};
        List<Flight> actualResultLessOrEqual = filterService.byArrivalTime(flights, FilterParam.LESSOREQUAL, depTime);
        List<Flight> actualResultMoreOrEqual = filterService.byArrivalTime(flights, FilterParam.MOREOREQUAL, depTime);
        List<Flight> actualResultEqual = filterService.byArrivalTime(flights, FilterParam.EQUAL, depTime);
        Assertions.assertEquals(expectedResultLessOrEqual, actualResultLessOrEqual);
        Assertions.assertEquals(expectedResultMoreOrEqual, actualResultMoreOrEqual);
        Assertions.assertEquals(expectedResultEqual, actualResultEqual);
    }
    @Test
    public void byTotalFlightTimeTest() {
        long flightHours = 7;
        long flightMinutes = 15;
        List<Flight> expectedResultLessOrEqual = new ArrayList<>() {{
            add(flight1);
            add(flight3);
        }};
        List<Flight> expectedResultMoreOrEqual = new ArrayList<>() {{
            add(flight2);
            add(flight3);
        }};
        List<Flight> expectedResultEqual = new ArrayList<>() {{
            add(flight3);
        }};
        List<Flight> actualResultLessOrEqual = filterService.byTotalFlightTime(flights, FilterParam.LESSOREQUAL, flightHours, flightMinutes);
        List<Flight> actualResultMoreOrEqual = filterService.byTotalFlightTime(flights, FilterParam.MOREOREQUAL, flightHours, flightMinutes);
        List<Flight> actualResultEqual = filterService.byTotalFlightTime(flights, FilterParam.EQUAL, flightHours, flightMinutes);
        Assertions.assertEquals(expectedResultLessOrEqual, actualResultLessOrEqual);
        Assertions.assertEquals(expectedResultMoreOrEqual, actualResultMoreOrEqual);
        Assertions.assertEquals(expectedResultEqual, actualResultEqual);
    }
    @Test
    public void byTotalEarthTimeTest() {
        long flightHours = 1;
        long flightMinutes = 15;
        List<Flight> expectedResultLessOrEqual = new ArrayList<>() {{
            add(flight2);
            add(flight3);
        }};
        List<Flight> expectedResultMoreOrEqual = new ArrayList<>() {{
            add(flight1);
            add(flight3);
        }};
        List<Flight> expectedResultEqual = new ArrayList<>() {{
            add(flight3);
        }};
        List<Flight> actualResultLessOrEqual = filterService.byTotalEarthTime(flights, FilterParam.LESSOREQUAL, flightHours, flightMinutes);
        List<Flight> actualResultMoreOrEqual = filterService.byTotalEarthTime(flights, FilterParam.MOREOREQUAL, flightHours, flightMinutes);
        List<Flight> actualResultEqual = filterService.byTotalEarthTime(flights, FilterParam.EQUAL, flightHours, flightMinutes);
        Assertions.assertEquals(expectedResultLessOrEqual, actualResultLessOrEqual);
        Assertions.assertEquals(expectedResultMoreOrEqual, actualResultMoreOrEqual);
        Assertions.assertEquals(expectedResultEqual, actualResultEqual);
    }
    @Test
    public void byTotalSkyTimeTest() {
        long flightHours = 6;
        long flightMinutes = 0;
        List<Flight> expectedResultLessOrEqual = new ArrayList<>() {{
            add(flight1);
            add(flight3);
        }};
        List<Flight> expectedResultMoreOrEqual = new ArrayList<>() {{
            add(flight2);
            add(flight3);
        }};
        List<Flight> expectedResultEqual = new ArrayList<>() {{
            add(flight3);
        }};
        List<Flight> actualResultLessOrEqual = filterService.byTotalSkyTime(flights, FilterParam.LESSOREQUAL, flightHours, flightMinutes);
        List<Flight> actualResultMoreOrEqual = filterService.byTotalSkyTime(flights, FilterParam.MOREOREQUAL, flightHours, flightMinutes);
        List<Flight> actualResultEqual = filterService.byTotalSkyTime(flights, FilterParam.EQUAL, flightHours, flightMinutes);
        Assertions.assertEquals(expectedResultLessOrEqual, actualResultLessOrEqual);
        Assertions.assertEquals(expectedResultMoreOrEqual, actualResultMoreOrEqual);
        Assertions.assertEquals(expectedResultEqual, actualResultEqual);
    }
    @Test
    public void byTotalTransfersTest() {
        long totalTransfers = 1;
        List<Flight> expectedResultLessOrEqual = new ArrayList<>();
        List<Flight> expectedResultMoreOrEqual = new ArrayList<>() {{
            add(flight1);
            add(flight2);
            add(flight3);
        }};
        List<Flight> expectedResultEqual = new ArrayList<>();
        List<Flight> actualResultLessOrEqual = filterService.byTotalTransfers(flights, FilterParam.LESSOREQUAL, totalTransfers);
        List<Flight> actualResultMoreOrEqual = filterService.byTotalTransfers(flights, FilterParam.MOREOREQUAL, totalTransfers);
        List<Flight> actualResultEqual = filterService.byTotalTransfers(flights, FilterParam.EQUAL, totalTransfers);
        Assertions.assertEquals(expectedResultLessOrEqual, actualResultLessOrEqual);
        Assertions.assertEquals(expectedResultMoreOrEqual, actualResultMoreOrEqual);
        Assertions.assertEquals(expectedResultEqual, actualResultEqual);
    }
    @Test
    public void byTotalSegments() {
        long totalSegments = 2;
        List<Flight> expectedResultLessOrEqual = new ArrayList<>();
        List<Flight> expectedResultMoreOrEqual = new ArrayList<>() {{
            add(flight1);
            add(flight2);
            add(flight3);
        }};
        List<Flight> expectedResultEqual = new ArrayList<>();
        List<Flight> actualResultLessOrEqual = filterService.byTotalSegments(flights, FilterParam.LESSOREQUAL, totalSegments);
        List<Flight> actualResultMoreOrEqual = filterService.byTotalSegments(flights, FilterParam.MOREOREQUAL, totalSegments);
        List<Flight> actualResultEqual = filterService.byTotalSegments(flights, FilterParam.EQUAL, totalSegments);
        Assertions.assertEquals(expectedResultLessOrEqual, actualResultLessOrEqual);
        Assertions.assertEquals(expectedResultMoreOrEqual, actualResultMoreOrEqual);
        Assertions.assertEquals(expectedResultEqual, actualResultEqual);
    }
}
