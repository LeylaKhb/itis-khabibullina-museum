package itis.khabibullina.dao.impl;

import itis.khabibullina.dao.Dao;
import itis.khabibullina.models.Program;
import itis.khabibullina.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramDaoImpl implements Dao<Program> {
    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public Program get(String name) {
        String sql = "SELECT * FROM PROGRAM WHERE name = " + name;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            Program program = null;

            if (resultSet != null) {
                while (resultSet.next()) {
                    program = new Program(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("people_count")
                    );
                }
            }
            return program;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Program> getAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from program";
            ResultSet resultSet = statement.executeQuery(sql);
            List<Program> programs = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    programs.add(
                            new Program(
                                    resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    resultSet.getInt("people_count")

                            )
                    );
                }
            }
            return programs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Program program) {
        String sql = "insert into program (name, people_count) values (?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, program.getName());
            preparedStatement.setInt(2, program.getPeopleCount());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
