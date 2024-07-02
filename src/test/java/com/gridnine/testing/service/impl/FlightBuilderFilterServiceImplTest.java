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
                        LocalDateTime.now().plusDays(2).plusHours(6),
                        LocalDateTime.now().plusDays(2).plusHours(8).plusMinutes(25)
                )
        );
        add(new Segment(
                        LocalDateTime.now().plusDays(2).plusHours(9),
                        LocalDateTime.now().plusDays(2).plusHours(12)
                )
        );
        add(new Segment(
                        LocalDateTime.now().plusDays(2).plusHours(13),
                        LocalDateTime.now().plusDays(2).plusHours(15)
                )
        );
    }});
    private final Flight flight2 = new Flight(new ArrayList<Segment>() {{
        add(new Segment(
                        LocalDateTime.now().plusDays(3).plusHours(1),
                        LocalDateTime.now().plusDays(3).plusHours(4)
                )
        );
        add(new Segment(
                        LocalDateTime.now().plusDays(3).plusHours(5),
                        LocalDateTime.now().plusDays(3).plusHours(7).plusMinutes(10)
                )
        );
        add(new Segment(
                        LocalDateTime.now().plusDays(3).plusHours(8),
                        LocalDateTime.now().plusDays(3).plusHours(13)
                )
        );
    }});
    //A flight departing in the past
    private final Flight flight3 = new Flight(new ArrayList<Segment>() {{
        add(new Segment(
                        LocalDateTime.now().minusDays(1).plusHours(1),
                        LocalDateTime.now().minusDays(1).plusHours(3)
                )
        );
        add(new Segment(
                        LocalDateTime.now().minusDays(1).plusHours(4),
                        LocalDateTime.now().minusDays(1).plusHours(8).plusMinutes(35)
                )
        );
        add(new Segment(
                        LocalDateTime.now().minusDays(1).plusHours(9),
                        LocalDateTime.now().minusDays(1).plusHours(11)
                )
        );
    }});

    //A flight that departs before it arrives
    private final Flight flight4 = new Flight(new ArrayList<Segment>() {{
        add(new Segment(
                        LocalDateTime.now().plusDays(8).plusHours(3),
                        LocalDateTime.now().plusDays(8).plusHours(6)
                )
        );
        add(new Segment(
                        LocalDateTime.now().plusDays(8).plusHours(7),
                        LocalDateTime.now().plusDays(8).plusHours(6)
                )
        );
        add(new Segment(
                        LocalDateTime.now().plusDays(8).plusHours(6),
                        LocalDateTime.now().plusDays(8).plusHours(11)
                )
        );
    }});
    //A flight with more than two hours ground time
    private final Flight flight5 = new Flight(new ArrayList<Segment>() {{
        add(new Segment(
                        LocalDateTime.now().plusDays(12).plusHours(1),
                        LocalDateTime.now().plusDays(12).plusHours(4)
                )
        );
        add(new Segment(
                        LocalDateTime.now().plusDays(12).plusHours(5),
                        LocalDateTime.now().plusDays(12).plusHours(8)
                )
        );
        add(new Segment(
                        LocalDateTime.now().plusDays(12).plusHours(9).plusMinutes(10),
                        LocalDateTime.now().plusDays(12).plusHours(12)
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
