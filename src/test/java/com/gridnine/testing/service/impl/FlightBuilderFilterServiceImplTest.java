package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightBuilderFilterServiceImplTest {
    @Mock
    FlightServiceImpl flightService = new FlightServiceImpl();
    @Mock
    FilterServiceImpl filterService = new FilterServiceImpl();
    @InjectMocks
    FlightBuilderFilterServiceImpl flightBuilderFilterService = new FlightBuilderFilterServiceImpl();

    private final Flight flight1 = new Flight(new ArrayList<Segment>() {{
        add(new Segment(
                        LocalDateTime.of(2024,8,30,15,00),
                        LocalDateTime.of(2024, 8, 30, 16, 15)
                )
        );
        add(new Segment(
                        LocalDateTime.of(2024,8,30,17,15),
                        LocalDateTime.of(2024, 8, 30, 18, 45)
                )
        );
        add(new Segment(
                        LocalDateTime.of(2024,8,30,19,30),
                        LocalDateTime.of(2024, 8, 30, 21, 10)
                )
        );
    }});
    private final Flight flight2 = new Flight(new ArrayList<Segment>() {{
        add(new Segment(
                        LocalDateTime.of(2024,7,02,10,00),
                        LocalDateTime.of(2024, 7, 02, 13, 30)
                )
        );
        add(new Segment(
                        LocalDateTime.of(2024,7,02,14,00),
                        LocalDateTime.of(2024, 7, 02, 17, 00)
                )
        );
        add(new Segment(
                        LocalDateTime.of(2024,7,02,17,30),
                        LocalDateTime.of(2024, 7, 02, 20, 00)
                )
        );
    }});
    //A flight departing in the past
    private final Flight flight3 = new Flight(new ArrayList<Segment>() {{
        add(new Segment(
                        LocalDateTime.of(2024,6,07,10,30),
                        LocalDateTime.of(2024, 6, 07, 11, 30)
                )
        );
        add(new Segment(
                        LocalDateTime.of(2024,6,07,12,00),
                        LocalDateTime.of(2024, 6, 07, 14, 00)
                )
        );
        add(new Segment(
                        LocalDateTime.of(2024,6,07,14,45),
                        LocalDateTime.of(2024, 6, 07, 17, 45)
                )
        );
    }});

    //A flight that departs before it arrives
    private final Flight flight4 = new Flight(new ArrayList<Segment>() {{
        add(new Segment(
                        LocalDateTime.of(2024,7,7,10,30),
                        LocalDateTime.of(2024, 7, 7, 11, 30)
                )
        );
        add(new Segment(
                        LocalDateTime.of(2024,7,7,12,00),
                        LocalDateTime.of(2024, 7, 7, 11, 30)
                )
        );
        add(new Segment(
                        LocalDateTime.of(2024,7,7,11,45),
                        LocalDateTime.of(2024, 7, 7, 13, 45)
                )
        );
    }});
    //A flight with more than two hours ground time
    private final Flight flight5 = new Flight(new ArrayList<Segment>() {{
        add(new Segment(
                        LocalDateTime.of(2024,9,07,10,30),
                        LocalDateTime.of(2024, 9, 07, 11, 30)
                )
        );
        add(new Segment(
                        LocalDateTime.of(2024,9,07,13,00),
                        LocalDateTime.of(2024, 9, 07, 14, 00)
                )
        );
        add(new Segment(
                        LocalDateTime.of(2024,9,07,16,45),
                        LocalDateTime.of(2024, 9, 07, 18, 45)
                )
        );
    }});
    private final List<Flight> flights = new ArrayList<>() {{
        add(flight1);
        add(flight2);
        add(flight3);
        add(flight4);
        add(flight5);
    }};
    @Test
    public void excludeByDepartureTimeTest() {
        List<Flight> expextedResult = new ArrayList<>();
        expextedResult.add(flight1);
        expextedResult.add(flight2);
        expextedResult.add(flight4);
        expextedResult.add(flight5);

        List<Flight> actualResult = flightBuilderFilterService.excludeByDepartureTime(flights);
        Assertions.assertEquals(expextedResult, actualResult);

    }
    @Test
    public void excludeByIncorrectSegmentDatesTest() {
        List<Flight> expextedResult = new ArrayList<>();
        expextedResult.add(flight1);
        expextedResult.add(flight2);
        expextedResult.add(flight3);
        expextedResult.add(flight5);

        List<Flight> actualResult = flightBuilderFilterService.excludeByIncorrectSegmentDates(flights);
        Assertions.assertEquals(expextedResult, actualResult);
    }
    @Test
    public void excludeByEarthTotalTimeTest() {
        List<Flight> expextedResult = new ArrayList<>();
        expextedResult.add(flight1);
        expextedResult.add(flight2);
        expextedResult.add(flight3);
        expextedResult.add(flight4);

        List<Flight> actualResult = flightBuilderFilterService.excludeByEarthTotalTime(flights);
        Assertions.assertEquals(expextedResult, actualResult);
    }
    @Test
    public void allRulesBuilderFilterTest() {
        List<Flight> expextedResult = new ArrayList<>();
        expextedResult.add(flight1);
        expextedResult.add(flight2);

        List<Flight> actualResult = flightBuilderFilterService.allRulesBuilderFilter(flights);
        Assertions.assertEquals(expextedResult, actualResult);
    }


}
