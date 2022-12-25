package com.dmdev.http.dao;

import com.dmdev.http.entyty.Flight;
import com.dmdev.http.entyty.FlightStatus;
import com.dmdev.http.util.ConnectionManeger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Long, Flight> {

    private static final FlightDao INSTANCE = new FlightDao();

    private FlightDao() {
    }

    public static final String FIND_ALL = """
            SELECT id, 
            flight_no, 
            departure_date,
            departure_airport_code, 
            arrival_date, 
            arrival_airport_code, 
            aircraft_id, 
            status
            FROM flight;
            """;

    @Override
    public List<Flight> findALL() {

        try (var connection = ConnectionManeger.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL)) {

            var resultSet = preparedStatement.executeQuery();
            List<Flight> flights = new ArrayList<>();
            while (resultSet.next())
            {
                flights.add(buildFlight(resultSet));
            }


        return flights;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Optional<Flight> findByID(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Flight entity) {

    }

    @Override
    public Flight save(Flight entity) {
        return null;
    }
    public static FlightDao getInstance()
    {
        return INSTANCE;
    }





    private static Flight buildFlight(ResultSet resultSet) throws SQLException {
        return new Flight(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("flight_no", String.class),
                resultSet.getObject("departure_date", Timestamp.class).toLocalDateTime(),
                resultSet.getObject("departure_airport_code", String.class),
                resultSet.getObject("arrival_date", Timestamp.class).toLocalDateTime(),
                resultSet.getObject("arrival_airport_code", String.class),
                resultSet.getObject("aircraft_id", Integer.class),
               FlightStatus.valueOf( resultSet.getObject("status", String.class))

        );
    }
}
