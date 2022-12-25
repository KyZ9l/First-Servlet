package com.dmdev.http.dto;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class FlightDto {
    private final Long id;
    private final String description;

}
