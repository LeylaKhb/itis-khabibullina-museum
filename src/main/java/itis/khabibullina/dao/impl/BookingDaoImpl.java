package itis.khabibullina.dao.impl;

import itis.khabibullina.dao.BookingDao;
import itis.khabibullina.dto.ExhibitionDto;
import itis.khabibullina.models.Booking;
import itis.khabibullina.models.Program;
import itis.khabibullina.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoImpl implements BookingDao<Booking> {
    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public Booking get(String hall) {
        String sql = "SELECT * FROM booking WHERE hall = " + hall;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            Booking booking = null;

            if (resultSet != null) {
                while (resultSet.next()) {
                    booking = new Booking(
                            resultSet.getInt("id"),
                            resultSet.getInt("hall"),
                            resultSet.getInt("program_id"),
                            resultSet.getDate("date")
                    );
                }
            }
            return booking;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Booking> getAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from booking";
            ResultSet resultSet = statement.executeQuery(sql);
            List<Booking> bookings = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    bookings.add(
                            new Booking(
                                    resultSet.getInt("id"),
                                    resultSet.getInt("hall"),
                                    resultSet.getInt("program_id"),
                                    resultSet.getDate("date")

                            )
                    );
                }
            }
            return bookings;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Booking booking) {
        String sql = "insert into booking (hall, program_id, date) values (?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, booking.getHall());
            preparedStatement.setInt(2, booking.getProgramId());
            preparedStatement.setDate(3, booking.getDate());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getBookingsCount(int programId, Date date, int hall) {
        String sql = "select count(*) from booking where program_id = " + programId +
                ", date = " + date + ", hall = " + hall;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            Integer count = 0;
            if (resultSet != null) {
                while (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
