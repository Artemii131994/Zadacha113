package dao;

import model.User;
import util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS testrt" +
            "(id int not null auto_increment," + " name VARCHAR(50), " + "lastname VARCHAR(50), " +
            "age int, " + "PRIMARY KEY (id))";
    private static final String DELETE_TABLE = "DROP TABLE IF EXISTS testrt";
    private static final String SAVA_USER = "INSERT INTO testrt(name, lastName, age) VALUES (?, ?, ?)";
    private static final String REMOVE_USER_BY_ID = "DELETE FROM testrt WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM testrt";
    private static final String CLEAN_USER = "TRUNCATE TABLE testrt";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() { // создание таблицы
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() { //удаление таблицы
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(DELETE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) { // сохранение user в таблицы
        try (Connection connection = Util.getConnection();
             PreparedStatement prepaSt = connection.prepareStatement(SAVA_USER)) {
            prepaSt.setString(1, name);
            prepaSt.setString(2, lastName);
            prepaSt.setString(3, String.valueOf(age));
            prepaSt.executeUpdate();
            System.out.println("User с именем " + name + " добавлен базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) { //удаление по id
        try (Connection connection = Util.getConnection();
             PreparedStatement prSt = connection.prepareStatement(REMOVE_USER_BY_ID)) {
            prSt.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() { // вывод всех user
        List<User> user = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet res = statement.executeQuery(GET_ALL)) {
            while (res.next()) {
                User user1 = new User();
                user1.setId(res.getLong("id"));
                user1.setName(res.getString("name"));
                user1.setLastName(res.getString("lastname"));
                user1.setAge(res.getByte("age"));
                user.add(user1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void cleanUsersTable() { // очистка таблицы
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(CLEAN_USER);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}