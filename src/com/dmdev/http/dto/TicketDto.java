package com.dmdev.http.dto;

import lombok.*;

@Data
@Value
public class TicketDto {
    Long id;
    Long flightId;
    String seatNo;

}
