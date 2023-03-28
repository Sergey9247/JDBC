package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    public UserDaoJDBCImpl() {

    }
    @Override
    public void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS users (Id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(20), lastName VARCHAR(20), age INT)";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(createTable)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS users";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(drop)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        String save = "INSERT INTO USERS (name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(save)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void removeUserById(long id) {
        String remove = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(remove)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public List < User > getAllUsers() {
        String all = "select * from users";
        List < User > users = new ArrayList < > ();
        try (ResultSet resultSet = Util.getConnection().createStatement().executeQuery(all)) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastName"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    @Override
    public void cleanUsersTable() {
        String clean = "TRUNCATE TABLE users";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(clean)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}