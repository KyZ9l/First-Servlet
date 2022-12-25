package com.dmdev.http.servise;

import com.dmdev.http.dao.TicketDao;
import com.dmdev.http.dto.TicketDto;
import com.dmdev.http.entyty.Ticket;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class TicketService {

    private static final TicketService INSTANCE = new TicketService();
    private final TicketDao ticketDao = TicketDao.getINSTANCE();

    private TicketService() {
    }

    public List<TicketDto> findAllByFlight(Long flightId)
    {
            return ticketDao.findAllByFlightId( flightId).stream()
                    .map(ticket -> new TicketDto(
                        ticket.getId(),
                            ticket.getFlightId(),
                            ticket.getSeatNo()
                    ))
                    .collect(toList());
    }

    public static TicketService getINSTANCE() {
        return INSTANCE;
    }
}
