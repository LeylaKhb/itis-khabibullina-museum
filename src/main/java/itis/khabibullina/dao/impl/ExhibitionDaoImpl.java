package itis.khabibullina.dao.impl;

import itis.khabibullina.dao.Dao;
import itis.khabibullina.dto.ExhibitionDto;
import itis.khabibullina.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExhibitionDaoImpl implements Dao<ExhibitionDto> {
    private final Connection connection = DatabaseConnectionUtil.getConnection();


    @Override
    public ExhibitionDto get(String hall) {
        String sql = "SELECT * FROM exhibition WHERE hall = " + hall;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ExhibitionDto exhibition = null;

            if (resultSet != null) {
                while (resultSet.next()) {
                    exhibition = new ExhibitionDto(
                            resultSet.getDate("start_date"),
                            resultSet.getDate("end_date"),
                            resultSet.getString("name"),
                            resultSet.getInt("hall")
                    );
                }
            }
            return exhibition;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ExhibitionDto> getAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from exhibition ORDER BY start_date";
            ResultSet resultSet = statement.executeQuery(sql);
            List<ExhibitionDto> exhibitions = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    exhibitions.add(
                            new ExhibitionDto(
                                    resultSet.getDate("start_date"),
                                    resultSet.getDate("end_date"),
                                    resultSet.getString("name"),
                                    resultSet.getInt("hall")
                            )
                    );
                }
            }
            return exhibitions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(ExhibitionDto exhibition) {
        String sql = "insert into exhibition (start_date, end_date, name, hall) values (?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, exhibition.getStartDate());
            preparedStatement.setDate(2, exhibition.getEndDate());
            preparedStatement.setString(3, exhibition.getName());
            preparedStatement.setInt(4, exhibition.getHall());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
