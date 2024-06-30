package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Test;
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
                        LocalDateTime.of(2024,06,07,10,30),
                        LocalDateTime.of(2024, 06, 07, 11, 30)
                )
        );
        add(new Segment(
                        LocalDateTime.of(2024,06,07,12,00),
                        LocalDateTime.of(2024, 06, 07, 14, 00)
                )
        );
        add(new Segment(
                        LocalDateTime.of(2024,06,07,14,45),
                        LocalDateTime.of(2024, 06, 07, 17, 45)
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

    }
}
