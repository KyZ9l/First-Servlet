package com.dmdev.http.servise;

import com.dmdev.http.dao.FlightDao;
import com.dmdev.http.dto.FlightDto;
import com.dmdev.http.entyty.Flight;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class FlightService {

    private static final FlightService INSTANCE = new FlightService();
        private final FlightDao flightDao = FlightDao.getInstance();

    private FlightService() {
    }

    public List<FlightDto> findAll() {

        return flightDao.findALL().stream()
                .map(flight -> new FlightDto(
                        flight.getId(),
                        """
                                %s - %s- %s
                                """.formatted(flight.getDepartureAirportCode(),flight.getArrivalAirportCode(),flight.getStatus() )
                ))
                .collect(toList());

    }

    public static FlightService getInstance()
    {
        return INSTANCE;
    }

}
